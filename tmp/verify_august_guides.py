"""Check guide excerpts against the dated commits, then compile and run snapshots."""
from pathlib import Path
import io
import re
import subprocess
import tempfile
import zipfile

root = Path(__file__).resolve().parents[1]
jdk = Path(r"C:\Program Files\Java\jdk-17\bin")

def git(*args):
    return subprocess.check_output(["git", *args], cwd=root)

with tempfile.TemporaryDirectory(prefix="august-commits-", dir=root / "tmp") as temporary:
    for day, revision in (("11", "a3cdee3"), ("12", "e59fc93")):
        guide = (root / f"스터디 실습 가이드/스터디_실습가이드_08월_{day}일.md").read_text(encoding="utf-8")
        assert guide.count("```") % 2 == 0
        blocks = re.findall(r"(be/javaPractice/src/[^\n]+\.java)\n\n```java\n(.*?)\n```", guide, re.S)
        assert len(blocks) == (7 if day == "11" else 11), len(blocks)
        committed_paths = set(git("diff-tree", "--no-commit-id", "--name-only", "-r", revision).decode("utf-8").splitlines())
        assert all(path in committed_paths or git("cat-file", "-e", f"{revision}:{path}") == b"" for path, _ in blocks)
        folder = Path(temporary) / day
        with zipfile.ZipFile(io.BytesIO(git("archive", "--format=zip", revision, "be/javaPractice/src", "be/javaPractice/lib"))) as archive:
            archive.extractall(folder)
        project = folder / "be/javaPractice"
        output = project / "bin"
        output.mkdir()
        lombok = project / "lib/lombok.jar"
        compiled = subprocess.run([str(jdk / "javac.exe"), "-encoding", "UTF-8", "-cp", str(lombok), "-processorpath", str(lombok), "-d", str(output), *map(str, (project / "src").rglob("*.java"))], capture_output=True)
        if day == "12":
            assert compiled.returncode != 0 and b"expected" in compiled.stderr
            broken = project / "src/features/operator/OperatorDemo.java"
            code = broken.read_text(encoding="utf-8")
            old = "default -> null;\n        }\n    }"
            assert code.count(old) == 1
            broken.write_text(code.replace(old, "default -> null;\n        };\n    }"), encoding="utf-8")
            compiled = subprocess.run([str(jdk / "javac.exe"), "-encoding", "UTF-8", "-cp", str(lombok), "-processorpath", str(lombok), "-d", str(output), *map(str, (project / "src").rglob("*.java"))], capture_output=True)
        assert compiled.returncode == 0, compiled.stderr

        def run(name, data="", ok=True):
            result = subprocess.run([str(jdk / "java.exe"), "-Dfile.encoding=UTF-8", "-cp", f"{output};{lombok}", name], input=data, text=True, encoding="utf-8", capture_output=True, timeout=10)
            assert (result.returncode == 0) == ok, result.stderr
            return result.stdout.strip(), result.stderr

        if day == "11":
            assert run("VariableApp")[0] == "name\t박준우\nage\t27\ngender\tm\nisMarried\tfalse"
            assert run("TeacherApp")[0].splitlines()[1:] == ["김영석", "화학 선생님"]
            assert run("CarApp")[0] == "Audi\nBMW\nBenz\nC200\nBenz\tC200"
            assert run("BlogApp")[0] == "Hi"
        else:
            assert run("StaticApp")[0].splitlines() == ["그냥 변수", "static 변수", "메시지 변경", "3.14", "메시지 변경", "static 변수", "3.14", "그냥 변수", "static 변수", "3.14"]
            assert run("StringApp")[0] == "OK\nFAIL\nOK"
            operator = run("OperatorApp")[0]
            assert "201\nOK\n400\nFAIL\n착하네\n5050\n" in operator
            assert operator.endswith("ABCDEFG\nstr length = 7\nG F E D C B A")
            # Zero is outside the random answer range; the original accepts it and fails all games.
            game = run("GuessGameApp", "0\n" * 30)[0]
            assert game.count("Enter number:") == 30 and game.count("You lost.") == 3
            assert "InputMismatchException" in run("GuessGameApp", "abc\n", False)[1]
            assert "NoSuchElementException" in run("GuessGameApp", "", False)[1]
            harness = project / "CommitCheck.java"
            harness.write_text("""
import features.operator.OperatorDemo;
import features.blogs.domain.dto.BlogRequestDTO;
import features.game.GuessGame;
public class CommitCheck {
    public static void main(String[] args) {
        OperatorDemo demo = new OperatorDemo();
        assert demo.register(new String("title1"), "", "").getStatus() == 400;
        assert demo.register2(new BlogRequestDTO(1, new String(""), "", "")).getStatus() == 201;
        assert demo.sumNumber(100, 1) == 5050;
        assert demo.WoodMan(3).equals(demo.WoodMan2(3));
        assert demo.WoodMan2(3).equals(demo.WoodMan3(3));
        System.setIn(new java.io.ByteArrayInputStream("10 90 42".getBytes()));
        GuessGame core = new GuessGame();
        assert !core.GuessGameCore(42);
        assert !core.GuessGameCore(42);
        assert core.GuessGameCore(42);
        GuessGame winner = new GuessGame() {
            public boolean GuessGameCore(int answer) { return true; }
        };
        assert winner.gameFor().startsWith("You got it in 1 tries.");
        assert winner.gameWhile().startsWith("You got it in 1 tries.");
        assert winner.gameDoWhile().startsWith("You got it in 1 tries.");
    }
}
""", encoding="utf-8")
            subprocess.run([str(jdk / "javac.exe"), "-encoding", "UTF-8", "-cp", f"{output};{lombok}", "-d", str(output), str(harness)], check=True)
            subprocess.run([str(jdk / "java.exe"), "-ea", "-cp", f"{output};{lombok}", "CommitCheck"], check=True, capture_output=True)
        print(f"PASS {day}: committed paths, snapshot compilation (12: documented semicolon fix), and application outputs")

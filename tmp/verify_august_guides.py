"""Run with Python from any directory; compile the Java examples in both guides."""
from pathlib import Path
import re
import subprocess
import tempfile

root = Path(__file__).resolve().parents[1]
jdk = Path(r'C:\Program Files\Java\jdk-17\bin')
lombok = root / 'be/javaPractice/lib/lombok.jar'
expected = {
    'VariablePractice': '0\nJava:3:true\n65\n3:3',
    'CarPractice': 'Audi:null\nBMW:null\nBenz:C200\nChanged\ntrue',
    'DtoPractice': 'null\nFirst\nnull\nHi\nstudy@example.com',
    'DtoFlow': '201:OK\n400:FAIL',
    'MemoryPractice': 'changed\noriginal\nupdated\n10',
    'FlowPractice': 'post=1\npre=3\nsum=5\ndivision=2,remainder=1\nPASS\nOTHER\nshort=false:0\nfull=false:1\nfor=1\nfor=3\nwhile=0\ndo=1\n2*1=2\n2*2=4\n2*3=6',
    'StringPractice': 'true\nfalse\ntrue\n3\nA\nBC\nJava Study',
}

with tempfile.TemporaryDirectory(prefix='august-guides-') as temporary:
    out = Path(temporary)
    sources = {}
    for day in ('11', '12'):
        guide = root / f'스터디 실습 가이드/스터디_실습가이드_08월_{day}일.md'
        content = guide.read_text(encoding='utf-8')
        assert content.count('```') % 2 == 0
        for name, code in re.findall(r'/([A-Za-z]+\.java)\n\n```java\n(.*?)\n```', content, re.S):
            folder = out / name.removesuffix('.java')
            folder.mkdir()
            source = folder / name
            source.write_text(code, encoding='utf-8')
            compiled = subprocess.run([str(jdk / 'javac.exe'), '-encoding', 'UTF-8', '-cp', str(lombok), '-processorpath', str(lombok), '-d', str(folder), str(source)], capture_output=True)
            assert compiled.returncode == 0, (name, compiled.stderr)
            sources[source.stem] = (folder, code)

    def run(name, inputs=''):
        result = subprocess.run([str(jdk / 'java.exe'), '-cp', f'{sources[name][0]};{lombok}', name], input=inputs, text=True, capture_output=True, timeout=10)
        assert result.returncode == 0, result.stderr
        return result.stdout.strip()

    assert len(sources) == 8, list(sources)
    for name, output in expected.items():
        assert run(name) == output, (name, run(name))
    success = run('GuessGame', '10\n90\n42\n')
    assert 'Up\n' in success and 'Down\n' in success
    assert success.endswith('Success in 3 tries. Answer: 42') and 'Failed.' not in success
    failed = run('GuessGame', '1\n' * 10)
    assert failed.count('Try ') == 10 and failed.endswith('Failed. Answer: 42')
    invalid = run('GuessGame', 'abc\n101\n42\n')
    assert 'Enter an integer.' in invalid and 'Out of range.' in invalid
    assert invalid.endswith('Success in 3 tries. Answer: 42')
    assert run('GuessGame').endswith('Input ended.')

    # Reproduce the guide's compile errors without changing its original examples.
    mutations = [
        ('VariablePractice', 'int local = 3;', 'int local;'),
        ('CarPractice', 'public Car() {}', ''),
        ('CarPractice', '// System.out.println(third.brand);', 'System.out.println(third.brand);'),
        ('DtoPractice', '@Getter @Setter', '@Getter'),
        ('MemoryPractice', '// MAX_TRIES = 20;', 'MAX_TRIES = 20;'),
    ]
    for index, (name, old, new) in enumerate(mutations):
        folder = out / f'negative-{index}'
        folder.mkdir()
        code = sources[name][1]
        assert old in code
        source = folder / f'{name}.java'
        source.write_text(code.replace(old, new), encoding='utf-8')
        result = subprocess.run([str(jdk / 'javac.exe'), '-encoding', 'UTF-8', '-cp', str(lombok), '-processorpath', str(lombok), '-d', str(folder), str(source)], capture_output=True)
        assert result.returncode != 0, name

print('PASS: 8 Java examples; exact console outputs; 4 game paths; 5 compile-error demonstrations.')

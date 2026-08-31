package features.jdbc;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import features.jdbc.domain.dto.DepartmentRequestDTO;
import features.jdbc.domain.dto.DepartmentResponseDTO;

public class MariadbDAO {
    private static final String DRIVER = "org.mariadb.jdbc.Driver";
    private static final String USER = "root";
    private static final String PASSWORD = "1229";
    private static final String URL = "jdbc:mariadb://localhost:3306/testdb";

    public MariadbDAO() {
        try {
            // 1. 메모리에 드라이버 로딩
            Class.forName(DRIVER);
            System.out.println("Driver loaded");
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            try {
                
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

    public List<DepartmentResponseDTO> departments() {
    Connection conn = null;  // DB와 연결
    PreparedStatement pstmt = null;  // query의 틀
    ResultSet rset = null;  // 결과 집합(가상의 테이블)
    String sql = "select * from department";  // query문
    List<DepartmentResponseDTO> list = new ArrayList<>();

        try {
            conn = DriverManager.getConnection(URL, USER, PASSWORD);  // 2. 연결 수립
            System.out.println("connected : " + conn);

            pstmt = conn.prepareStatement(sql);  // 3. SQL query 수행을 위한 작업
            rset = pstmt.executeQuery();  // 4. 실행

            // 5. 결과 핸들링
            while (rset.next()) {
                list.add(DepartmentResponseDTO.builder()
                    .dept_id(rset.getString(1))
                    .dept_name(rset.getString(2))
                    .loc_id(rset.getString(3))
                    .build());
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            try {
                if (conn != null) { conn.close(); }  // 5. 연결 종료
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        return list;
    }

    public int update(DepartmentRequestDTO request) {
        Connection          conn  = null ; 
        PreparedStatement   pstmt = null ; 
        String  sql = """
                update  department
                set     dept_name = ?
                where   dept_id   = ?
                """;  
        try {
            conn = DriverManager.getConnection(URL, USER, PASSWORD); 
            System.out.println("connected : " + conn); 

            pstmt = conn.prepareStatement(sql);
            // sql의 n번째 ?에 들어갈 파라미터 설정
            pstmt.setString(1, request.getDept_name());
            pstmt.setString(2, request.getDept_id());
            return pstmt.executeUpdate();
        } catch(Exception e) {
            e.printStackTrace();
        } finally {
            try{
                if( conn != null) { conn.close(); } 
            } catch(Exception e) {
                e.printStackTrace(); 
            }
        }
        return 0 ;
    }
}
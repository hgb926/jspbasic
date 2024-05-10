package com.jsp.chap05;

import javax.imageio.plugins.jpeg.JPEGImageReadParam;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

// mariadb 연결 및 CRUD
public class jdbcBasic {

    // 필요한 데이터
    private String username = "root"; // db 계정명
    private String password = "mariadb"; // db 패스워드
    private String url = "jdbc:mariadb://localhost:3306/spring5"; // db url : 데이터베이스 설치 위치
    private String driverClassName = "org.mariadb.jdbc.Driver"; // db 벤더(회사)별 전용 커넥터 클래스




    // DELETE 기능
    public void delete(int id) {

        // 1. 연결 드라이버 로딩
        try (Connection conn = DriverManager.getConnection(url, username, password);) {
            Class.forName(driverClassName);

            // 2. 데이터베이스 접속
//            Connection conn = DriverManager.getConnection(url, username, password);

            // 3. 실행할 SQL 생성
            String sql = "DELETE FROM tbl_person " +
                    "WHERE id = ?";

            // 4. SQL 실행 객체 생성
            PreparedStatement pstmt = conn.prepareStatement(sql);

            // 5. ? 값 채우기
            pstmt.setInt(1,id);

            // 6. 실행 명령
            // INSERT, UPDATE, DELETE 같은 명령 사용
            pstmt.executeUpdate();

            // 7. 데이터베이스 연결 해제
//            pstmt.close();
            // ↑ try 문에서 이미 해결

        } catch (Exception e) {
            e.printStackTrace();
        }
    }



    // UPDATE 기능
    public void update(int id, String newName, int newAge) {

        // 1. 연결 드라이버 로딩
        try (Connection conn = DriverManager.getConnection(url, username, password);) {
            Class.forName(driverClassName);

            // 2. 데이터베이스 접속
//            Connection conn = DriverManager.getConnection(url, username, password);

            // 3. 실행할 SQL 생성
            String sql = "UPDATE tbl_person " +
                    "SET person_name = ?, person_age = ? " +
                    "WHERE id = ?";

            // 4. SQL 실행 객체 생성
            PreparedStatement pstmt = conn.prepareStatement(sql);

            // 5. ? 값 채우기
            pstmt.setString(1,newName);
            pstmt.setInt(2,newAge);
            pstmt.setInt(3,id);

            // 6. 실행 명령
            // INSERT, UPDATE, DELETE 같은 명령 사용
            pstmt.executeUpdate();

            // 7. 데이터베이스 연결 해제
//            pstmt.close();
            // ↑ try 문에서 이미 해결

        } catch (Exception e) {
            e.printStackTrace();
        }
    }



    // SELECT
    public List<Person> findAll() {
        try (Connection conn = DriverManager.getConnection(url, username, password)){

            Class.forName(driverClassName);

            String sql = "SELECT * FROM tbl_person " +
                    "ORDER BY id DESC";

            // SQL 실행 객체 생성
            PreparedStatement pstmt = conn.prepareStatement(sql);

            // ? 채우기
            // ?가 없으므로 생략

            // 실행 명령 - SELECT 는 다른 메서드를 사용
            // ResultSet :  SELECT 의 결과집합 표를 가져옴
            ResultSet rs = pstmt.executeQuery();

            // ResultSet 데이터 가져오기
            // next() 의 리턴 타입은 boolean 이라 쉽게 반복문 사용 가능
            // rs.next(); // 표의 행을 지목하는 커서
            List<Person> people = new ArrayList<>();
           while (rs.next()) {
            // 커서가 가리키는 행의 데이터를 하나씩 추출
            // columnLabel 은 컬럼명.
            int id = rs.getInt("id");
            String personName = rs.getString("person_name");
            int personAge = rs.getInt("person_age");

            Person person = new Person(id, personName, personAge);
            people.add(person);

           }
            return people;

        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }

    }


}

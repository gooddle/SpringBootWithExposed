package org.example.exposed.domain.infra.database

import jakarta.annotation.PostConstruct
import org.example.exposed.domain.infra.database.util.DatabaseUtil
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Component
import javax.sql.DataSource

@Component
class DatabaseInitializer(
    @Autowired private val dataSource: DataSource
) {

    private val databaseUtil: DatabaseUtil = DatabaseUtil(dataSource)  // Spring DataSource를 이용한 DatabaseUtil 초기화

    @PostConstruct
    fun init() {
        // 애플리케이션 시작 시 테이블을 생성
        databaseUtil.createTables()  // 클래스 메서드 호출
    }
}
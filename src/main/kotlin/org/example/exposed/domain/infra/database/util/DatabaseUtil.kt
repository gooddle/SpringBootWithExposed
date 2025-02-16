package org.example.exposed.domain.infra.database.util

import org.example.exposed.domain.user.table.UserTable
import org.jetbrains.exposed.sql.Database
import org.jetbrains.exposed.sql.SchemaUtils
import org.jetbrains.exposed.sql.transactions.transaction
import javax.sql.DataSource

class DatabaseUtil(
    private val dataSource: DataSource) {

    // Spring DataSource를 Exposed에 연결
    fun connectToDatabase() {
        Database.connect(dataSource)  // Spring Boot의 DataSource를 Exposed에 연결
    }

    // 테이블 생성
    fun createTables() {
        connectToDatabase()  // 데이터베이스 연결

        transaction {
            SchemaUtils.create(UserTable)  // UserTable 생성
        }
    }
}
package br.com.fiap.clarimail

// Email.kt
data class Email(
    val id: Long,
    val sender: String,
    val subject: String,
    val content: String,
    val timestamp: Long
)

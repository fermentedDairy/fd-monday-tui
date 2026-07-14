package org.fermented.dairy.tui.monday.auth

interface AuthService {
    fun authenticate(): Boolean
    fun getToken(): String?
    fun saveToken(token: String)
    fun clearToken()
}
package org.fermented.dairy.tui.monday.auth

class AuthServiceImpl : AuthService {
    private var token: String? = null
    
    override fun authenticate(): Boolean {
        // In a real implementation, this would validate the token with the API
        return token != null
    }
    
    override fun getToken(): String? {
        return token
    }
    
    override fun saveToken(token: String) {
        this.token = token
        // In a real implementation, we'd securely store this token
    }
    
    override fun clearToken() {
        this.token = null
        // In a real implementation, we'd delete the stored token file
    }
}

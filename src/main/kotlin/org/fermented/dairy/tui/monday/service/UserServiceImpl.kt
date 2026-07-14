package org.fermented.dairy.tui.monday.service

import org.fermented.dairy.tui.monday.config.AppConfig
import org.fermented.dairy.tui.monday.model.User

class UserServiceImpl(private val config: AppConfig) : UserService {
    private var currentUser: User? = null
    
    override fun getCurrentUser(): User? {
        return currentUser
    }
    
    override fun setCurrentUser(user: User) {
        this.currentUser = user
    }
}
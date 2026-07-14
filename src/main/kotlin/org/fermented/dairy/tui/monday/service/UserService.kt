package org.fermented.dairy.tui.monday.service

import org.fermented.dairy.tui.monday.model.User

interface UserService {
    fun getCurrentUser(): User?
    fun setCurrentUser(user: User)
}
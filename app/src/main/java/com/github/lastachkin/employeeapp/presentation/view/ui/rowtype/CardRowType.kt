package com.github.lastachkin.employeeapp.presentation.view.ui.rowtype

class CardRowType(avatar: String, name: String, tag: String, position: String): RowType {
    var avatarUrl: String? = null
    var name: String? = null
    var tag: String? = null
    var position: String? = null

    init {
        this.avatarUrl = avatar
        this.name = name
        this.tag = tag
        this.position = position
    }
}
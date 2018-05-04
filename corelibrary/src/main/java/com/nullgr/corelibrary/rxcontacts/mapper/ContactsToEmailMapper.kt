package com.nullgr.corelibrary.rxcontacts.mapper

import android.database.Cursor
import android.provider.ContactsContract
import com.nullgr.corelibrary.rxcontacts.domain.ContactEmail
import com.nullgr.corelibrary.rxcontacts.extensions.has

/**
 * Created by Grishko Nikita on 01.02.18.
 */
internal object CursorToEmailMapper : CursorMapper<List<ContactEmail>> {

    override fun map(cursor: Cursor?, vararg arguments: String): List<ContactEmail> {
        cursor?.let {
            val emailResult = arrayListOf<ContactEmail>()
            cursor.use { cursor ->
                if (cursor.moveToFirst()) {
                    do {
                        val id = cursor.getInt(
                                cursor.getColumnIndex(ContactsContract.CommonDataKinds.Email._ID))
                        if (!(emailResult has id)) {
                            val displayName = cursor.getString(
                                    cursor.getColumnIndex(ContactsContract.CommonDataKinds.Email.DISPLAY_NAME))
                            val email = cursor.getString(
                                    cursor.getColumnIndex(ContactsContract.CommonDataKinds.Email.DATA1))
                            val contactEmail = ContactEmail(id.toLong(), displayName, email)
                            emailResult.add(contactEmail)
                        }
                    } while (cursor.moveToNext())
                }
            }
            return emailResult
        }
        return emptyList()
    }
}
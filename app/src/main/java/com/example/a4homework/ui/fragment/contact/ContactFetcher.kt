package com.example.a4homework.ui.fragment.contact

import android.annotation.SuppressLint
import android.content.ContentResolver
import android.content.Context
import android.provider.ContactsContract
import android.provider.ContactsContract.Contacts

class ContactFetcher(private val context: Context) {


    @SuppressLint("Range")
    fun fetchContacts(): ArrayList<ContactModel> {
        val contacts = ArrayList<ContactModel>()

        val contentResolver : ContentResolver = context.contentResolver


        val cursor = contentResolver.query(
            ContactsContract.CommonDataKinds.Phone.CONTENT_URI,
            null,
            null,
            null,
            null
        )

        cursor?.let {
            if (it.count >0) {
                while (it.moveToNext()) {
                    val id =
                        it.getString(it.getColumnIndex(Contacts._ID))
                    val name =
                        it.getString(it.getColumnIndex(Contacts.DISPLAY_NAME))

                    val contact = ContactModel(id, name)
                    contacts.add(contact)
                }
            }
            it.close()
        }
        return contacts
    }
}
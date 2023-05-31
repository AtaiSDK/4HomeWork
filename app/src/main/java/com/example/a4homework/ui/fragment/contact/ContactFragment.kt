package com.example.a4homework.ui.fragment.contact

import android.R
import android.annotation.SuppressLint
import android.content.Intent
import android.content.pm.PackageManager
import android.database.Cursor
import android.net.Uri
import android.provider.ContactsContract
import android.provider.ContactsContract.CommonDataKinds.Phone
import android.widget.Button
import android.widget.ListView
import android.widget.SimpleCursorAdapter
import androidx.core.app.ActivityCompat
import com.example.a4homework.base.BaseFragment
import com.example.a4homework.databinding.FragmentContactBinding


class ContactFragment : BaseFragment<FragmentContactBinding>(FragmentContactBinding::inflate),
    ContactAdapter.Result {

    private val adapter: ContactAdapter by lazy {
        ContactAdapter(this)
    }

    override fun setupUI() {
        binding.rvContact.adapter = adapter

        if (ActivityCompat.checkSelfPermission(requireContext(), android.Manifest.permission.READ_CONTACTS
            )
            != PackageManager.PERMISSION_GRANTED
        ) {
            ActivityCompat.requestPermissions(requireActivity(), Array(1,) {
                android.Manifest.permission.READ_CONTACTS
            }, 111)
            getContacts()
        } else {
            getContacts()
        }
    }


    override fun setupObserver() {

    }

    @SuppressLint("Range")
    fun getContacts() {
        val list = arrayListOf<ContactModel>()

        val contentResolver = requireContext().contentResolver

        val cursor = contentResolver.query(
            ContactsContract.Contacts.CONTENT_URI,
            null,
            null,
            null,
            Phone.DISPLAY_NAME
        )

        if (cursor?.count!! > 0) {
            while (cursor.moveToNext()) {
                if (Integer.parseInt(
                        cursor.getString(
                            cursor.getColumnIndex(
                                ContactsContract.Contacts.HAS_PHONE_NUMBER
                            )
                        )
                    ) > 0
                ) {
                    val id = cursor.getString(
                        cursor.getColumnIndex(ContactsContract.Contacts._ID)
                    )

                    val name = cursor.getString(
                        cursor.getColumnIndex(ContactsContract.Contacts.DISPLAY_NAME)
                    )

                    val numberCursor = contentResolver.query(
                        Phone.CONTENT_URI,
                        null,
                        Phone.CONTACT_ID + "=?",
                        arrayOf(id),
                        null
                    )

                    if (numberCursor?.moveToNext()!!) {
                        val phoneNumber =
                            numberCursor.getString(numberCursor.getColumnIndex(Phone.NUMBER))
                        numberCursor.close()
                        list.add(ContactModel(name = name, id = phoneNumber))
                    }
                    numberCursor.close()
                }
            }
            cursor.close()
            adapter.setList(list)
        }
    }

    override fun call(model: ContactModel) {
        val intent = Intent(Intent.ACTION_DIAL)
        intent.data = Uri.parse("tel:" + model.id)
        startActivity(intent)
    }

    override fun chat(model: ContactModel) {
        val number = "https://wa.me/${model.id}?text=Hello!"
        val intent = Intent(Intent.ACTION_VIEW)
        intent.data = Uri.parse(number)
        startActivity(intent)
    }
}
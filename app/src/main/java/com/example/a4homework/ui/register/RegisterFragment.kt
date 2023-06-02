package com.example.a4homework.ui.register

import android.net.Uri
import android.os.Parcel
import android.os.Parcelable
import android.util.Log
import androidx.activity.result.contract.ActivityResultContracts
import androidx.navigation.fragment.findNavController
import com.example.a4homework.base.BaseFragment
import com.example.a4homework.databinding.FragmentRegisterBinding
import com.example.a4homework.ui.App

class RegisterFragment() : BaseFragment<FragmentRegisterBinding>(FragmentRegisterBinding::inflate),
    Parcelable {
    constructor(parcel: Parcel) : this() {
    }

    private lateinit var imageUri: Uri

    override fun setupUI() {
        binding.btnRegister.setOnClickListener {
            saveUser()
            App.prefs.saveUser()
            findNavController().navigateUp()
        }
        saveBtnListener()
    }

    private fun saveBtnListener() {
        val resultLauncher = registerForActivityResult(
            ActivityResultContracts.GetContent()) {
            imageUri = it!!
            binding.imgProfile.setImageURI(imageUri)
        }

        binding.imgProfile.setOnClickListener {
            resultLauncher.launch("image/")
        }
    }

    private fun saveUser() {
        val imageString = imageUri.toString()
        App.prefs.saveUserImage(imageString)

        val name = binding.etRegisterName.text.toString()
        App.prefs.saveUserName(name)
        val surname = binding.etRegisterSurname.text.toString()
        App.prefs.saveUserSurname(surname)
        val number = binding.etRegisterPhoneNumber.text.toString()
        if(number.isNotEmpty()){
            try{
                val numberInt = number.toInt()
                App.prefs.saveUserNumber(numberInt)
            }catch (e: java.lang.NumberFormatException){
                Log.e("ololo", "Exception: ${e.message}")
            }
        }else{
            Log.e("ololo", "Number is empty")
        }

        binding.btnMarriedSwitch.setOnCheckedChangeListener{
            _, isChecked ->
            if(isChecked){
                App.prefs.isUserMarried(true)
            }else{
                App.prefs.isUserMarried(false)
            }
        }
    }

    override fun writeToParcel(parcel: Parcel, flags: Int) {

    }

    override fun describeContents(): Int {
        return 0
    }

    companion object CREATOR : Parcelable.Creator<RegisterFragment> {
        override fun createFromParcel(parcel: Parcel): RegisterFragment {
            return RegisterFragment(parcel)
        }

        override fun newArray(size: Int): Array<RegisterFragment?> {
            return arrayOfNulls(size)
        }
    }


}
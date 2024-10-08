package com.harold.eshopbasic.viewmodel

import androidx.lifecycle.ViewModel
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.FirebaseUser
import com.harold.eshopbasic.data.User
import com.harold.eshopbasic.utils.Resource
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableStateFlow
import javax.inject.Inject


@HiltViewModel
class RegisterViewModel @Inject constructor(
    private val firebaseAuth: FirebaseAuth
): ViewModel() {
    fun createAccountWithEmailAndPassword(user: User, password: String) {

        val _register = MutableStateFlow<Resource<FirebaseUser>>(Resource.Loading())
       // val register: Flow<Resource<FirebaseUser>> = _register

        firebaseAuth.createUserWithEmailAndPassword(user.email,password)
            .addOnSuccessListener {
                it.user?.let {
                    _register.value = Resource.Succes(it)
                }
            }.addOnFailureListener {
                _register.value = Resource.Error(it.message.toString())

            }
    }
}
package com.mkshmnv.djinni.ui.auth

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.google.firebase.auth.ktx.auth
import com.google.firebase.ktx.Firebase
import com.mkshmnv.djinni.App
import com.mkshmnv.djinni.Logger
import com.mkshmnv.djinni.R
import com.mkshmnv.djinni.Toast

class AuthPagerViewModel : ViewModel() {
    private val tag = this::class.simpleName!!

    // Get context
    private val context = App.instance

    // Initialize Firebase Auth
    private var auth = Firebase.auth

    // LiveData
    private val _authSignInSuccess = MutableLiveData<Boolean>()
    val authSignInSuccess: LiveData<Boolean> = _authSignInSuccess
    private val _authSignUpSuccess = MutableLiveData<Boolean>()
    val authSignUpSuccess: LiveData<Boolean> = _authSignUpSuccess
    private val _authSignInWithGoogleSuccess = MutableLiveData<Boolean>()
    val authSignInWithGoogleSuccess: LiveData<Boolean> = _authSignInWithGoogleSuccess

    fun signIn(email: String, password: String) {
        if (checkFilledFields(email, password)) return
        auth.signInWithEmailAndPassword(email, password)
            .addOnCompleteListener { task ->
                if (task.isSuccessful) {
                    val user = auth.currentUser
                    Toast.showWithLogger(context, "Welcome, ${user?.email}", "signIn $tag")
                    _authSignInSuccess.value = true
                } else {
                    Toast.showWithLogger(
                        context,
                        task.exception!!.message.toString(),
                        "signIn $tag"
                    )
                }
            }
    }

    fun signInWithGoogle() {
        Toast.showWithLogger(context, "Google sign in not implemented yet", tag)
    }

    fun signUp(email: String, password: String) {
        if (checkFilledFields(email, password)) return
        auth.createUserWithEmailAndPassword(email, password)
            .addOnCompleteListener { task ->
                if (task.isSuccessful) {
                    val user = auth.currentUser
                    // Sign in success, update UI with the signed-in user's information
                    Toast.showWithLogger(context, "Welcome, ${user?.email}", "signUp $tag")
                    _authSignUpSuccess.value = true
                } else {
                    // If sign in fails, display a message to the user.
                    Toast.showWithLogger(
                        context,
                        task.exception!!.message.toString(),
                        "signUp $tag"
                    )
                    Logger.logcat(task.exception.toString(), tag)
                }
            }
    }

    fun signOut() {
        auth.signOut()
    }

    private fun checkFilledFields(email: String?, password: String?): Boolean {
        return if (email.isNullOrEmpty() && password.isNullOrEmpty()) {
            Toast.showWithLogger(
                context,
                context.getString(R.string.please_fill_all_fields),
                "checkFilledFields"
            )
            true
        } else {
            false
        }
    }
}
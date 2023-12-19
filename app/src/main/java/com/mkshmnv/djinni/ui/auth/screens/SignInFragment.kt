package com.mkshmnv.djinni.ui.auth.screens

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.navigation.NavController
import androidx.navigation.fragment.findNavController
import com.mkshmnv.djinni.Logger
import com.mkshmnv.djinni.R
import com.mkshmnv.djinni.databinding.FragmentSignInBinding
import com.mkshmnv.djinni.repository.NavControllerProvider
import com.mkshmnv.djinni.repository.UserViewModel
import com.mkshmnv.djinni.ui.MainActivity
import com.mkshmnv.djinni.ui.viewBinding

class SignInFragment : Fragment(R.layout.fragment_sign_in), NavControllerProvider {
    private val binding: FragmentSignInBinding by viewBinding()
    private val userViewModel: UserViewModel by activityViewModels()
    // For logger
    private val tag = this::class.simpleName!!

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Hide action bar and bottom menu for this fragment
        showMenuAndActionBar(false)
        return super.onCreateView(inflater, container, savedInstanceState)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        userViewModel.apply {
            Logger.logcat("onViewCreated with user - $this", tag)
            binding.apply {
                // TODO: TESTs remove
                etSignInEmail.setText("1@1.com")
                etSignInPassword.setText("111111")
                btnSignIn.setOnClickListener {
                    val email = etSignInEmail.text.toString()
                    val password = etSignInPassword.text.toString()
                    signInUser(email = email, password = password)
                }
                btnSignInWithGoogle.setOnClickListener {
                    signInWithGoogle()
                }
            }
            authorizedUser.observe(viewLifecycleOwner) {
                Logger.logcat("authorizedUser observe : $it", tag)
                navigateToProfile(this@SignInFragment)
                onDestroy()
            }
        }
    }

    override fun onDestroy() {
        super.onDestroy()
        Logger.logcat("onDestroy and removeObservers", tag)
        userViewModel.authorizedUser.removeObservers(viewLifecycleOwner)
        // Show action bar and bottom menu for this fragment
        showMenuAndActionBar(true)
    }

    override fun getNavController(): NavController {
        return findNavController()
    }

    private fun showMenuAndActionBar(show: Boolean) {
        val mainActivity = activity as? MainActivity
        mainActivity?.showMenuAndActionBar(show)
    }
}
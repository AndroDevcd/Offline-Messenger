package com.intuisoft.offlinemessenger.presentation

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.navigation.NavController
import androidx.navigation.Navigation
import androidx.recyclerview.widget.LinearLayoutManager
import com.intuisoft.offlinemessenger.R
import com.intuisoft.offlinemessenger.presentation.adapters.RecyclerViewAdapter
import com.intuisoft.offlinemessenger.presentation.util.injectVM
import com.intuisoft.offlinemessenger.presentation.viewmodel.MainActivityViewModel


class MainActivity : AppCompatActivity() {

    val VOICE_REQ_CODE = 100

    private lateinit var layoutManager : LinearLayoutManager;
    private lateinit var adapter : RecyclerViewAdapter;
    lateinit var navController: NavController
    private val messagesViewModel: MainActivityViewModel by lazy {
        injectVM(this, MainActivityViewModel::class.java)
    }


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_home)
        navController = Navigation.findNavController(this, R.id.nav_host_fragment_container)

//        answerEditText.setOnKeyListener(View.OnKeyListener(
//            fun (view : View, keyCode : Int, event : KeyEvent) : Boolean {
//                if (event.getAction()!=KeyEvent.ACTION_DOWN)
//                    return true
//
//                if(keyCode == KeyEvent.KEYCODE_ENTER) {
//                    hideKeyboard()
//                    return true
//                }
//
//                return false
//            }
//        ))

//        messagesViewModel.getLiveData()!!.observe(this, Observer { items->
//            adapter.submitList(items)
//            messageList.smoothScrollToPosition(items.size)
//        })

//        layoutManager = LinearLayoutManager(this);
//        layoutManager.setStackFromEnd(true);
//        conversationList.layoutManager = layoutManager;
//        adapter =
//            RecyclerViewAdapter(this)
//        conversationList.adapter = adapter
    }


    fun hideKeyboard() {
//        val imm = (getSystemService(Context.INPUT_METHOD_SERVICE) as? InputMethodManager)
//        imm!!.hideSoftInputFromWindow(answerEditText.windowToken, 0)
    }

//    fun onVoiceBtnClicked(view : View) {
//        val intent = Intent(RecognizerIntent.ACTION_RECOGNIZE_SPEECH);
//        intent.putExtra(
//            RecognizerIntent.EXTRA_LANGUAGE_MODEL,
//            RecognizerIntent.LANGUAGE_MODEL_FREE_FORM
//        )
//        intent.putExtra(RecognizerIntent.EXTRA_LANGUAGE, Locale.getDefault())
//        intent.putExtra(RecognizerIntent.EXTRA_PROMPT, "Tap to stop listening")
//        try {
//            startActivityForResult(intent, VOICE_REQ_CODE)
//        } catch (a: ActivityNotFoundException) {
//            Toast.makeText(
//                applicationContext,
//                "Sorry your device does not support speech-to-text",
//                Toast.LENGTH_SHORT
//            ).show()
//        }
//    }

//    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
//        super.onActivityResult(requestCode, resultCode, data)
//        when (requestCode) {
//             VOICE_REQ_CODE -> {
//                if (resultCode == RESULT_OK && data != null) {
//                    val result = data
//                        .getStringArrayListExtra(RecognizerIntent.EXTRA_RESULTS);
//                    if(result != null)
//                        answerEditText.setText(answerEditText.text.toString() + result.get(0));
//                        answerEditText.setSelection(answerEditText.text.toString().length)
//                        answerEditText.requestFocus()
//                }
//            }
//        }
//    }
}

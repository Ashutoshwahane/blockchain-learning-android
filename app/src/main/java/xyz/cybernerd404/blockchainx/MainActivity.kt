package xyz.cybernerd404.blockchainx

import android.content.Context
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.MotionEvent
import android.view.inputmethod.InputMethodManager
import android.widget.Toast
import androidx.recyclerview.widget.LinearLayoutManager
import xyz.cybernerd404.blockchainx.databinding.ActivityMainBinding
import java.security.Timestamp
import java.text.SimpleDateFormat
import java.util.*

class MainActivity : AppCompatActivity() {

    lateinit var blockAdapter: BlockAdapter
    lateinit var binding: ActivityMainBinding
    lateinit var list: MutableList<BlockModel>

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        list = arrayListOf()
        blockAdapter = BlockAdapter()

        binding.blockRv.layoutManager = LinearLayoutManager(this)
        binding.blockRv.adapter = blockAdapter

       /* binding.addMessageBtn.setOnClickListener {
            val bottomSheet = BottomMessageFragment()
            bottomSheet.show(supportFragmentManager,bottomSheet.tag)
        }*/

        var previousHash = "0"

        binding.addIv.setOnClickListener {

            if (binding.addMessageEt.text.isEmpty()){
                Toast.makeText(this, "Message is Empty", Toast.LENGTH_SHORT).show()
            }else{
                val blockModel = BlockModel()
                blockModel.message = binding.addMessageEt.text.toString()
                blockModel.previous_hash = previousHash
                blockModel.hash = hashString(blockModel, "SHA-256")
                previousHash = blockModel.hash
                blockModel.time_stamp = SimpleDateFormat("yyyy/MM/dd/ hh-mm").format(Date().time)
                Log.d("debug", "date: $blockModel")
                list.add(blockModel)
                blockAdapter.setBlock(list)
                binding.addMessageEt.text.clear()

            }

        }

    }

    override fun dispatchTouchEvent(ev: MotionEvent?): Boolean {
        if (currentFocus != null) {
            val imm = getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager
            imm.hideSoftInputFromWindow(currentFocus!!.windowToken, 0)
        }
        return super.dispatchTouchEvent(ev)
    }
}
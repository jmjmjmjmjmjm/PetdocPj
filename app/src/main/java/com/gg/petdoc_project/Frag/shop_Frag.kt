package com.gg.petdoc_project.Frag

import android.content.ContentValues
import android.content.Intent
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.gg.petdoc_project.*
import com.gg.petdoc_project.Dtos.BoardDto
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.ktx.Firebase
import kotlinx.android.synthetic.main.fragment_shop_.*

class shop_Frag : Fragment() {
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_shop_, container, false)
    }

    override fun onStart() {
        super.onStart()
        write.setOnClickListener { writego() }
        (activity as HomeActivity).read(context, readall(), recyclerView)

        swipe.setOnRefreshListener {
            (activity as HomeActivity).read(context, readall(), recyclerView)
            swipe.isRefreshing = false
        }
    }

    fun writego() {
//        (activity as HomeActivity).read(context,readall(),recyclerView)
        activity?.let {
            val intent = Intent(context, BoardWrite::class.java)
            startActivity(intent)
        }
    }

    fun readall(): ArrayList<BoardDto> {
        var listdata: ArrayList<BoardDto> = ArrayList<BoardDto>()
        var db: FirebaseFirestore = Firebase.firestore
        db.collection("board")
            .get()
            .addOnSuccessListener { result ->
                for (document in result) {
//                    Log.d(""+document, "${document.id} => ${document.data}")
                    var a = document.data.toString()
                    var b = a.split(",", "{", "}", "[", "]")
                    Log.d("데이터확인", "" + b[1] + b[2] + b[3] + b[4] + b[5] + b[6] + b[7])
                    var anymal = b[1].split("=")
                    var uid = b[2].split("=")
                    var img = b[3].split("=")
                    var title = b[4].split("=")
                    var content = b[5].split("=")
                    var createDate = b[6].split("=")
                    var username = b[7].split("=")
                    var dto = BoardDto(
                        uid[1],
                        username[1],
                        anymal[1],
                        title[1],
                        content[1],
                        img[1],
                        createDate[1]
                    )
                    listdata.add(dto)
                }
            }
            .addOnFailureListener { exception ->
                Log.d(ContentValues.TAG, "Error getting documents: ", exception)
            }
        return listdata
    }
}
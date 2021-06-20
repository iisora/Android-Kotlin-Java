package com.websarva.wings.android.listviewsample2

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.AdapterView
import android.widget.ArrayAdapter
import android.widget.ListView

// the procedure for Display Dialog on Android
// 1.Create Class which inherits the DialogFragment.
// 2.Describe processing for generate Dialog in onCreateDialog() and return generated DialogObject.
// 3.generate Object of 1 in Activity and execute show().
class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        //ListView オブジェクトを取得。
        val lvMenu = findViewById<ListView>(R.id.lvMenu)
        // リストビューに表示するリストデータを作成。
        var menuList = mutableListOf("唐揚げ定食", "ハンバーグ定食", "生姜焼き定食",
        "ステーキ定食", "野菜炒め定食", "とんかつ定食", "ミンチカツ定食", "チキンカツ定食",
        "コロッケ定食", "回鍋肉定食", "麻婆豆腐定食", "青椒肉絲定食", "焼き魚定食", "焼肉定食")
        // アダプタオブジェクトを生成
        val adapter = ArrayAdapter(this@MainActivity, android.R.layout.simple_list_item_1,
        menuList)
        // リストビューにアダプタオブジェクトを設定。
        lvMenu.adapter = adapter

        // config Listener to ListView
        lvMenu.onItemClickListener = ListItemClickListener()
    }

    // MemberClass: process when tapped List
    private inner class ListItemClickListener: AdapterView.OnItemClickListener {
        override fun onItemClick(parent:AdapterView<*>, view: View, position:Int, id:Long) {
            // generate Order Confirm Dialog Fragment Object
            val dialogFragment = OrderConfirmDailogFragment()
            // display Dialog
            // pass the Arbitrary string(etc .. Class Name). This is String to Identify Dialog.
            dialogFragment.show(supportFragmentManager, "OrderConfirmDialogFragment")
        }
    }
}
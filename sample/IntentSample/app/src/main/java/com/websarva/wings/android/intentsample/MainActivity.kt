package com.websarva.wings.android.intentsample

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.AdapterView
import android.widget.ListView
import android.widget.SimpleAdapter

// 1.generate Intent Class )
// 2.store Data to pass to the Next Start Activity(use: putExtra())
// 3.Start Activity
class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        // get ListView of View component
        val lvMenu = findViewById<ListView>(R.id.lvMenu)
        // prepare MutableList Object for using in SimpleAdapter
        val menuList: MutableList<MutableMap<String, String>> = mutableListOf()
        // Prepare "MapObject" to store the data of 「唐揚げ定食」, and register the data with "menuList"
        var menu = mutableMapOf("name" to "唐揚げ定食", "price" to "800yen")
        // menu is "Map" in consists of "name" and "price". menuList is consists of multiplex menu in other words "Map".
        // More specifically, MutableList is consists of multiplex MutableMap which is the One chunk of Data.
        // So to speak, MutableList<MutableMap<String,*>> is a pseudo DataBase!!
        menuList.add(menu)
        // Prepare "MapObject" to store the data of 「ハンバーグ定食」, and register the data with "menuList"
        menu = mutableMapOf("name" to "ハンバーグ定食", "price" to "850yen")
        menuList.add(menu)
        // Repeat the above...
        menu = mutableMapOf("name" to "焼き魚定食", "price" to "820yen")
        menuList.add(menu)
        menu = mutableMapOf("name" to "闇の定食", "price" to "83yen")
        menuList.add(menu)
        menu = mutableMapOf("name" to "トマホーク定食", "price" to "1089yen")
        menuList.add(menu)
        menu = mutableMapOf("name" to "純金定食", "price" to "10000000yen")
        menuList.add(menu)
        menu = mutableMapOf("name" to "錬金定食", "price" to "free")
        menuList.add(menu)
        menu = mutableMapOf("name" to "目玉のソテー", "price" to "30yen")
        menuList.add(menu)
        menu = mutableMapOf("name" to "長友さんの作った鍋", "price" to "1050yen")
        menuList.add(menu)
        menu = mutableMapOf("name" to "本田さんの作った鍋", "price" to "1050yen")
        menuList.add(menu)
        menu = mutableMapOf("name" to "闘莉王さんの作った鍋", "price" to "1050yen")
        menuList.add(menu)
        menu = mutableMapOf("name" to "長谷部さんの作った鍋", "price" to "1050yen")
        menuList.add(menu)
        menu = mutableMapOf("name" to "香川さんの作った鍋", "price" to "1050yen")
        menuList.add(menu)
        menu = mutableMapOf("name" to "川島さんの作った鍋", "price" to "1050yen")
        menuList.add(menu)
        menu = mutableMapOf("name" to "メッシさんの作った鍋", "price" to "1050yen")
        menuList.add(menu)
        menu = mutableMapOf("name" to "ラウールさんの作った鍋", "price" to "1050yen")
        menuList.add(menu)
        menu = mutableMapOf("name" to "ロナウドさんの作った鍋", "price" to "1050yen")
        menuList.add(menu)
      menu = mutableMapOf("name" to "中島さんの作った鍋", "price" to "1050yen")
        menuList.add(menu)


        // Prepare "SimpleAdapter forth argument Data for from.
        val from = arrayOf("name", "price")
        // Prepare "SimpleAdapter fifth argment Data for to.
        val to = intArrayOf(android.R.id.text1, android.R.id.text2)
        // generate "SimpleAdapter"
        // SimpleAdapter
        // SimpleAdapterでは、fromとtoの組み合わせでMutableMap内のどのデータをListView各行のどの部品に割り当てるかを指定できる
        val adapter = SimpleAdapter(this@MainActivity, menuList, android.R.layout.simple_list_item_2, from, to)
        // register Adapter
        lvMenu.adapter = adapter
        // register "ListenerClass" in case of tapped list.
        lvMenu.onItemClickListener = ListItemClickListener()
    }

    // MemberClass has processing which is described when the list is tapped
    private inner class ListItemClickListener: AdapterView.OnItemClickListener {
        override fun onItemClick(parent: AdapterView<*>, view: View, position: Int, id: Long) {
            // get data of the tapped line. data of a one line is MutableMap Model in SimpleAdapter!
            val item = parent.getItemAtPosition(position) as MutableMap<String, String>
            // get set meal and price
            val menuName = item["name"]
            val menuPrice = item["price"]
            // generate IntentObject
            val intent2MenuThanks = Intent(this@MainActivity, MenuThanksActivity::class.java)
            // store the data to send to next page.
            intent2MenuThanks.putExtra("menuName", menuName)
            intent2MenuThanks.putExtra("menuPrice", menuPrice)
            // start next page
            startActivity(intent2MenuThanks)
        }

    }

}
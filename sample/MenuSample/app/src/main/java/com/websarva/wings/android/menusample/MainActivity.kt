package com.websarva.wings.android.menusample

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.ContextMenu
import android.view.Menu
import android.view.MenuItem
import android.view.View
import android.widget.AdapterView
import android.widget.ListView
import android.widget.SimpleAdapter
import android.widget.Toast

class MainActivity : AppCompatActivity() {

    // ListData which is displayed to ListView
    private var _menuList: MutableList<MutableMap<String, Any>> = mutableListOf()
    // Property to use for forth argment of SimpleAdapter.
    private val _from = arrayOf("name", "price")
    // Property to use for fifth argument of SimpleAdapter.
    private val _to = intArrayOf(R.id.tvMenuNameRow, R.id.tvMenuPriceRow)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        // Prepare 'Set Meal Menu' List Object with private method, and store in property.
        _menuList = createBeefBowlList()
        // get 画面部品ListView
        val lvMenu = findViewById<ListView>(R.id.lvMenu)
        // generate SimpleAdapter
        val adapter = SimpleAdapter(this@MainActivity, _menuList, R.layout.row, _from, _to)
        // register Adapter
        lvMenu.adapter = adapter
        // register Listener Class for List Tapping
        lvMenu.onItemClickListener = ListItemClickListener()

        registerForContextMenu(lvMenu)
    }

    // procedure to display Option Menu
    // 1. create .xml file for Option Menu.
    // 2. describe dedicated(specify) tag in .xml file.
    // 3. Implement the onCreateOptionsMenu() Method in Activity.
    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        // inflate R.menu.menu_options_menu_list
        menuInflater.inflate(R.menu.menu_options_menu_list, menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        var returnVal = true
        // branch of processing by ID's 'R' value of selected Menu.
        when(item.itemId) {
            // processing in case of selected set Meal menu.
            R.id.menuListOptionBeefBowl ->
                // generate Set Meal List Data.
                _menuList = createBeefBowlList()
            R.id.menuListOptionDessert ->
                // generate curry menu list
                _menuList = createDessertList()
            // otherwise
            else ->
                // Call Same Name method of Super Class and let the return value be returnVal.
                returnVal = super.onOptionsItemSelected(item)
        }
        // get the Display Component List View
        val lvMenu = findViewById<ListView>(R.id.lvMenu)
        // generate SimpleAdapter with selected menu data.
        val adapter = SimpleAdapter(this@MainActivity, _menuList, R.layout.row, _from, _to)
        // register Adapter
        lvMenu.adapter = adapter
        return returnVal
    }

    override fun onContextItemSelected(item: MenuItem): Boolean {
        var returnVal = true
        // get Object has some information bout long pressed view
        val info = item.menuInfo as AdapterView.AdapterContextMenuInfo
        // get position of long pressed list.
        val listPosition = info.position
        // get Map object of long pressed menu from position.
        val menu = _menuList[listPosition]

        // branch of processing by R.value of long pressed menu's id.
        when(item.itemId) {
            R.id.menuListContextDesc -> {
                // get description of menu.
                val desc = menu["description"] as String
                // display toast
                Toast.makeText(this@MainActivity, desc, Toast.LENGTH_LONG).show()
            }
            R.id.menuListContextOrder -> {
                order(menu)
            }
            else -> {
                returnVal = super.onContextItemSelected(item)
            }
        }
        return returnVal
    }

    override fun onCreateContextMenu(menu: ContextMenu, view: View, menuInfo: ContextMenu.ContextMenuInfo) {
        // Call same name method of super class.
        super.onCreateContextMenu(menu, view, menuInfo)
        // inflate xml file for context menu.
        menuInflater.inflate(R.menu.menu_context_menu_list, menu)
        // config header title of context menu.
        menu.setHeaderTitle(R.string.menu_list_context_header)
    }


    private fun createBeefBowlList(): MutableList<MutableMap<String, Any>> {

        // Prepare ListObject for menulist of set meal.
        val menuList: MutableList<MutableMap<String, Any>> = mutableListOf()
        // prepare MapObject for storeing data of 'karage' set meal and register data with menuList.
        // "desc" = description
        var menu = mutableMapOf<String, Any>("name" to "牛丼", "price" to 800,
        "description" to "牛丼。紅生姜をかけるのがおすすめ。")
        menuList.add(menu)
        // prepare MapObject for storeing data of 'humburger' set meal and register data with menuList.
        menu = mutableMapOf("name" to "ハンバーグ丼", "price" to 850, "description" to
        "ハンバーグをご飯に乗っけただけ！部活帰りの野球部に是非！")
        menuList.add(menu)
        // repeat the above
        menu = mutableMapOf("name" to "蟹味噌脳味噌茶漬け丼", "price" to 90, "description" to
        "ポックルの脳味噌のやつ。")
        menuList.add(menu)
        menu = mutableMapOf("name" to "ただめし丼", "price" to 10, "description" to
        "無料ではない。")
        menuList.add(menu)
        menu = mutableMapOf("name" to "光るご飯Donn", "price" to 780, "description" to
        "ニトリの懐中電灯と同じ値段？気のせいでしょ。。。")
        menuList.add(menu)
        menu = mutableMapOf("name" to "ミキティオムレツ丼", "price" to 900, "description" to
        "庄司の嫁ではない。彼女のレンタル代はもっと高い。正体はケニアの木の実である。")
        menuList.add(menu)
        return menuList
    }

    private fun createDessertList(): MutableList<MutableMap<String,Any>> {
        // prepare List Object for dessert Menu List.
        val menuList: MutableList<MutableMap<String, Any>> = mutableListOf()
        // prepare Map Object for storing Data of 'べっこうアメ', and register data with menuList
        var menu = mutableMapOf<String, Any>("name" to "べっこう飴", "price" to 520,
        "description" to "コスト２０円で作っております。")
        menuList.add(menu)
        menu = mutableMapOf("name" to "スーパーカップ", "price" to 420, "description" to
        "向かいのコンビニで買ってきたスーパーカップ。")
        menuList.add(menu)

        return menuList
    }

    // MemberClass has processing which is described something when the list is tapped
    private inner class ListItemClickListener: AdapterView.OnItemClickListener {
        override fun onItemClick(parent: AdapterView<*>, view: View, position: Int, id: Long) {
            // get data of the tapped line. data of a one line is MutableMap Model in SimpleAdapter!
            val item = parent.getItemAtPosition(position) as MutableMap<String, Any>
//            // get set meal and price
//            val menuName = item["name"] as String
//            val menuPrice = item["price"] as Int
//            // generate IntentObject
//            val intent2MenuThanks = Intent(this@MainActivity, MenuThanksActivity::class.java)
//            // store the data to send to next page.
//            intent2MenuThanks.putExtra("menuName", menuName)
//            intent2MenuThanks.putExtra("menuPrice", "${menuPrice}円")
//            // start next page
//            startActivity(intent2MenuThanks)

            // order process
            order(item)
        }

    }

    private fun order(menu: MutableMap<String, Any>) {
        // get set meal and price. because value part of Map is Any Model, this is need cast.
        val menuName = menu["name"] as String
        val menuPrice = menu["price"] as Int

        // generate Intent Object
        val intent2MenuThanks = Intent(this@MainActivity, MenuThanksActivity::class.java)
        // store data to send second page.
        intent2MenuThanks.putExtra("menuName", menuName)
        intent2MenuThanks.putExtra("menuPrice","${menuPrice}円")
        // start second page
        startActivity(intent2MenuThanks)
    }
}
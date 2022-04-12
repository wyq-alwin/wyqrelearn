package com.example.wyqrelearn

import android.app.Activity
import android.content.Intent
import android.os.Build
import android.os.Bundle
import android.util.Log
import android.view.MenuItem
import android.view.View
import android.widget.TextView
import android.widget.Toast
import androidx.annotation.RequiresApi
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.GravityCompat
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.repeatOnLifecycle
import com.example.wyqrelearn.MainActivity.Companion.TAG
import com.example.wyqrelearn.databinding.ActivityFirstBinding
import com.google.android.material.snackbar.Snackbar
import com.google.android.material.tabs.TabLayoutMediator
import kotlinx.coroutines.launch

@RequiresApi(Build.VERSION_CODES.R)
class FirstActivity : AppCompatActivity() {

    private lateinit var mediator: TabLayoutMediator

    private lateinit var binding: ActivityFirstBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        Log.d(TAG, "FirstActivity onCreate()")
        binding = ActivityFirstBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val name = intent.getStringExtra("name")
        val intent = Intent().apply {
            putExtra("result", "接收到: $name, 回传：我是回传的数据！")
        }
        setResult(Activity.RESULT_OK, intent)

        setSupportActionBar(binding.toolbar)
        supportActionBar?.let {
            it.setDisplayHomeAsUpEnabled(true)
            it.setHomeButtonEnabled(true)
            it.setHomeAsUpIndicator(R.drawable.ic_menu)
        }
        binding.navigationView.let { view ->
            view.setNavigationItemSelectedListener {
                it.isChecked = true
                binding.mainDrawer.closeDrawers()
                Toast.makeText(this, it.title.toString(), Toast.LENGTH_LONG).show()
                return@setNavigationItemSelectedListener true
            }
        }
        initViewPager()
        binding.root.post {

        }
        lifecycleScope.launch{

        }
    }

    fun checkin(view: View) {
        Snackbar.make(view, "点击成果", Snackbar.LENGTH_SHORT).show()
    }

//    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
//        menuInflater.inflate(R.menu.menu_overaction, menu)
//        return true
//    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when (item.itemId) {
            android.R.id.home -> {
                binding.mainDrawer.openDrawer(GravityCompat.START)
                return true
            }

        }
        return super.onOptionsItemSelected(item)
    }

    private fun initViewPager() {
        val titles: MutableList<String> = ArrayList()
        titles.add("精选")
        titles.add("体育")
        titles.add("巴萨")
        titles.add("购物")
        titles.add("明星")
        titles.add("视频")
        titles.add("健康")
        titles.add("励志")
        titles.add("图文")
        titles.add("本地")
        titles.add("动漫")
        titles.add("搞笑")
        titles.add("精选")
        val fragments = ArrayList<ListFragment>()
        titles.forEach {
            binding.tabs.newTab().apply {
                text = it
            }

            fragments.add(ListFragment())
        }
        binding.viewpager.adapter = MyFragmentAdapter(this, fragments)
        mediator = TabLayoutMediator(binding.tabs, binding.viewpager) { tab, index ->
            //这里可以自定义TabView
            val tabView = TextView(this)
            tabView.text = titles[index]
            tab.customView = tabView
        }
        //要执行这一句才是真正将两者绑定起来
        //要执行这一句才是真正将两者绑定起来
        mediator.attach()
    }

    override fun onNewIntent(intent: Intent?) {
        super.onNewIntent(intent)
        Log.d(TAG, "FirstActivity onNewIntent()")
    }

    override fun onResume() {
        Log.d(TAG, "FirstActivity onResume()")
        super.onResume()
    }

    override fun onDestroy() {
        mediator.detach()
        super.onDestroy()
    }
}
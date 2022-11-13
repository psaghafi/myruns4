package com.example.parmida_saghafi_myruns2

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import androidx.fragment.app.Fragment
import androidx.viewpager.widget.ViewPager
import androidx.viewpager2.widget.ViewPager2
import com.google.android.material.tabs.TabLayout
import com.google.android.material.tabs.TabLayoutMediator

class MainActivity : AppCompatActivity() {
    private lateinit var start: StartFragment
    private lateinit var history: HistoryFragment
    private lateinit var settings:SettingsFragment
    private lateinit var fragments:ArrayList<Fragment>
    private lateinit var tabLayout: TabLayout
    private lateinit var viewPager2:ViewPager2
    private lateinit var myFragmentStateAdapter: MyFragmentStateAdapter
    private val tabTitles=arrayOf("Start", "History", "Settings")
    private lateinit var tabConfigurationStrategy: TabLayoutMediator.TabConfigurationStrategy
    private lateinit var tabLayoutMediator: TabLayoutMediator
    private lateinit var startButton: Button

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        start= StartFragment()
        history= HistoryFragment()
        settings= SettingsFragment()
        fragments=ArrayList()
        fragments.add(start)
        fragments.add(history)
        fragments.add(settings)



        tabLayout=findViewById(R.id.tab)
        viewPager2=findViewById(R.id.viewpager)
        myFragmentStateAdapter= MyFragmentStateAdapter(this, fragments)
        viewPager2.adapter=myFragmentStateAdapter

        tabConfigurationStrategy=TabLayoutMediator.TabConfigurationStrategy(){
            tab: TabLayout.Tab, position: Int->
            tab.text=tabTitles[position]
        }
        tabLayoutMediator= TabLayoutMediator(tabLayout, viewPager2, tabConfigurationStrategy)
        tabLayoutMediator.attach()




    }

    override fun onDestroy() {
        super.onDestroy()
        tabLayoutMediator.detach()
    }

}
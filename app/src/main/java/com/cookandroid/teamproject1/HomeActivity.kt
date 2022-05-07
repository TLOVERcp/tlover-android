package com.cookandroid.teamproject1

import android.content.Intent
import android.os.Bundle
import android.os.PersistableBundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.ui.NavigationUI
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.cookandroid.teamproject1.databinding.ActivityHomeBinding
import com.cookandroid.teamproject1.databinding.FragmentHomeBinding
import com.cookandroid.teamproject1.databinding.SelectDestBinding

/**
 * 홈액티비티 -> 메인엑티비티라고 생각하면 됨
 */
class HomeActivity : AppCompatActivity(){
    private lateinit var mbinding : ActivityHomeBinding


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        mbinding = ActivityHomeBinding.inflate(layoutInflater)

        setContentView(mbinding.root)

        //네비게이션들을 담는 호스트
        val navHostFragment = supportFragmentManager.findFragmentById(R.id.my_nav_host) as NavHostFragment

        //네비게이션 컨트롤러
        val navController = navHostFragment.navController

        //바텀 네비게이션 뷰와 네비게이션을 묶어준다.
        NavigationUI.setupWithNavController(mbinding.myBottomNav, navController)

        //바텀 네비게이션 출력하는 부분과 그렇지 않은 부분을 나눔
        navController.addOnDestinationChangedListener { _, destination, _ ->
            if(destination.id == R.id.homeFragment || destination.id == R.id.searchFragment || destination.id == R.id.diaryFragment
                || destination.id == R.id.myInfoFragment) {
                mbinding.myBottomNav.visibility = View.VISIBLE
                mbinding.myNavHost.setPadding(0,0,0,0)}
            else {
                mbinding.myBottomNav.visibility = View.GONE
                mbinding.myNavHost.setPadding(0,0,0,0)
            }
        }

    }

}
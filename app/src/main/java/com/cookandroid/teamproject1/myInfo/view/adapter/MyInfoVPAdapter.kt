package com.cookandroid.teamproject1.myInfo.view.adapter

import androidx.fragment.app.Fragment
import androidx.viewpager2.adapter.FragmentStateAdapter
import com.cookandroid.teamproject1.diary.view.fragment.DiaryLibraryFragment
import com.cookandroid.teamproject1.myInfo.view.fragment.MyInfoLikeFragment
import com.cookandroid.teamproject1.myInfo.view.fragment.MyInfoRecentFragment
import com.cookandroid.teamproject1.myInfo.view.fragment.MyInfoScrapFragment
import com.cookandroid.teamproject1.plan.view.fragment.DiaryPlanFragment

/**
 * myinfo ViewPager2 adapter
 * 스크랩 or 좋아요 or 최근본 다이어리
 */
class MyInfoVPAdapter(fragment : Fragment) : FragmentStateAdapter(fragment){
    override fun getItemCount(): Int {
        return 3
    }

    override fun createFragment(position: Int): Fragment {
        return when(position){
            0 -> MyInfoScrapFragment()
            1 -> MyInfoLikeFragment()
            else -> MyInfoRecentFragment()
        }
    }

}
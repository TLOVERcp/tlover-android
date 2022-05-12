package com.cookandroid.teamproject1.diary.view.adapter

import androidx.fragment.app.Fragment
import androidx.viewpager2.adapter.FragmentStateAdapter
import com.cookandroid.teamproject1.diary.view.fragment.DiaryLibraryFragment
import com.cookandroid.teamproject1.plan.view.fragment.DiaryPlanFragment

/**
 * diary ViewPager2 adapter
 * 다이어리목록화면 or 계획목록화면
 */
class DiaryVPAdapter(fragment : Fragment) : FragmentStateAdapter(fragment){
    override fun getItemCount(): Int {
        return 2
    }

    override fun createFragment(position: Int): Fragment {
        return when(position){
            0 -> DiaryPlanFragment()
            else -> DiaryLibraryFragment()
        }
    }

}
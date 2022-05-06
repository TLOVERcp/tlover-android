package com.cookandroid.teamproject1

import androidx.fragment.app.Fragment
import androidx.viewpager2.adapter.FragmentStateAdapter
import com.cookandroid.teamproject1.plan.view.fragment.DiaryPlanFragment

/**
 * diary ViewPager2 adapter
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
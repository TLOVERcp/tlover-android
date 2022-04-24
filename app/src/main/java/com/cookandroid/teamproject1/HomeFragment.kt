package com.cookandroid.teamproject1

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.cookandroid.teamproject1.databinding.FragmentHomeBinding

class HomeFragment : Fragment(){

    private var mBinding : FragmentHomeBinding?= null
    private var dataList = ArrayList<HomeDataModel>()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val binding = FragmentHomeBinding.inflate(inflater, container, false)

        mBinding = binding

        // RVAdapter

        dataList.apply{
            add(HomeDataModel("절경이네요 장관이고요", R.drawable.img1_item_home_random))
            add(HomeDataModel("titee", R.drawable.img2_item_home_random))
            add(HomeDataModel("tit", R.drawable.img1_item_home_random))
            add(HomeDataModel("titee", R.drawable.img2_item_home_random))
            add(HomeDataModel("tit", R.drawable.img1_item_home_random))
            add(HomeDataModel("titee", R.drawable.img2_item_home_random))

        }

        val homeRVAdapter = HomeRVAdapter(dataList)
        binding.fragmentHomeTitleRandomRv.adapter = homeRVAdapter
        binding.fragmentHomeTitleRandomRv.layoutManager = LinearLayoutManager(context, LinearLayoutManager.HORIZONTAL, false)

        return mBinding?.root
    }

    override fun onDestroyView() {
        mBinding = null
        super.onDestroyView()
    }


}
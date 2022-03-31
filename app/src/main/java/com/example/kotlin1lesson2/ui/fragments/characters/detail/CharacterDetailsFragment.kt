package com.example.kotlin1lesson2.ui.fragments.characters.detail

import android.util.Log
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.navArgs
import by.kirich1409.viewbindingdelegate.viewBinding
import com.example.kotlin1lesson2.R
import com.example.kotlin1lesson2.base.BaseFragment
import com.example.kotlin1lesson2.base.BaseViewModel
import com.example.kotlin1lesson2.common.extensions.setImage
import com.example.kotlin1lesson2.common.resource.Resource
import com.example.kotlin1lesson2.databinding.FragmentDetailBinding
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.launch

@AndroidEntryPoint
class CharacterDetailsFragment : BaseFragment<FragmentDetailBinding, BaseViewModel>(
    R.layout.fragment_detail
) {
    //    private lateinit var ivUserAvatar: ImageView
//    private var EXPAND_AVATAR_SIZE: Float = 0F
//    private var COLLAPSE_IMAGE_SIZE: Float = 0F
//    private var horizontalToolbarAvatarMargin: Float = 0F
//    private var cashCollapseState: Pair<Int, Int>? = null
//
//
    override val binding by viewBinding(FragmentDetailBinding::bind)
    override val viewModel: CharacterDetailsViewModel by viewModels()
    private val args: CharacterDetailsFragmentArgs by navArgs()
//    private var avatarAnimateStartPointY: Float = 0F
//    private var avatarCollapseAnimationChangeWeight: Float = 0F
//    private var isCalculated = false
//    private var verticalToolbarAvatarMargin = 0F
//    private lateinit var background: FrameLayout
//
//
//    override fun setupRequests() {
//        EXPAND_AVATAR_SIZE = resources.getDimension(R.dimen.default_expanded_image_size)
//        COLLAPSE_IMAGE_SIZE = resources.getDimension(R.dimen.default_collapsed_image_size)
//        horizontalToolbarAvatarMargin = resources.getDimension(R.dimen.activity_margin)
//        (binding.animToolbar.height - COLLAPSE_IMAGE_SIZE) * 2
//
//        binding.appBarLayout.addOnOffsetChangedListener(
//            AppBarLayout.OnOffsetChangedListener { appBarLayout, i ->
//                if (isCalculated.not()) {
//                    avatarAnimateStartPointY =
//                        abs((appBarLayout.height - (EXPAND_AVATAR_SIZE + horizontalToolbarAvatarMargin)) / appBarLayout.totalScrollRange)
//                    avatarCollapseAnimationChangeWeight = 1 / (1 - avatarAnimateStartPointY)
//                    verticalToolbarAvatarMargin =
//                        (binding.animToolbar.height - COLLAPSE_IMAGE_SIZE) * 2
//                    isCalculated = true
//                }
//
//                updateViews(abs(i / appBarLayout.totalScrollRange.toFloat()))
//            })
//
//    }
//
//    private fun updateViews(offset: Float) {
//        /* apply levels changes*/
//        when (offset) {
//            in 0.15F..1F -> {
//                binding.tvProfileName.apply {
//                    if (visibility != View.VISIBLE) visibility = View.VISIBLE
//                    alpha = (1 - offset) * 0.35F
//                }
//            }
//
//            in 0F..0.15F -> {
//                binding.animToolbar.alpha = (1f)
//                ivUserAvatar.alpha = 1f
//            }
//        }
//
//        /** collapse - expand switch*/
//        when {
//            offset < SWITCH_BOUND -> Pair(TO_EXPANDED, cashCollapseState?.second ?: WAIT_FOR_SWITCH)
//            else -> Pair(TO_COLLAPSED, cashCollapseState?.second ?: WAIT_FOR_SWITCH)
//        }.apply {
//            when {
//                cashCollapseState != null && cashCollapseState != this -> {
//                    when (first) {
//                        TO_EXPANDED -> {
//                            /* set avatar on start position (center of parent frame layout)*/
//                            ivUserAvatar.translationX = 0F
//                            /**/
//                            background.setBackgroundColor(
//                                ContextCompat.getColor(
//                                    requireContext(),
//                                    R.color.color_transparent
//                                )
//                            )
//                            /* hide top titles on toolbar*/
//                            binding.tvWorkaround.visibility = View.INVISIBLE
//                        }
//                        TO_COLLAPSED -> background.apply {
//                            alpha = 0F
//                            setBackgroundColor(
//                                ContextCompat.getColor(
//                                    requireContext(),
//                                    R.color.colorPrimary
//                                )
//                            )
//                            animate().setDuration(250).alpha(1.0F)
//
//                            /* show titles on toolbar with animation*/
//                            binding.collapsingToolbar.apply {
//                                visibility = View.VISIBLE
//                                alpha = 0F
//                                animate().setDuration(500).alpha(1.0f)
//                            }
//                        }
//                    }
//                    cashCollapseState = Pair(first, SWITCHED)
//                }
//                else -> {
//                    cashCollapseState = Pair(first, WAIT_FOR_SWITCH)
//                }
//            }
//
//            /* Collapse avatar img*/
//            ivUserAvatar.apply {
//                when {
//                    offset > avatarAnimateStartPointY -> {
//                        val avatarCollapseAnimateOffset =
//                            (offset - avatarAnimateStartPointY) * avatarCollapseAnimationChangeWeight
//                        val avatarSize =
//                            EXPAND_AVATAR_SIZE - (EXPAND_AVATAR_SIZE - COLLAPSE_IMAGE_SIZE) * avatarCollapseAnimateOffset
//                        this.layoutParams.also {
//                            it.height = Math.round(avatarSize)
//                            it.width = Math.round(avatarSize)
//                        }
//                        binding.tvProfileNameSingle.setTextSize(TypedValue.COMPLEX_UNIT_PX, offset)
//
//                        this.translationX =
//                            ((binding.appBarLayout.width - horizontalToolbarAvatarMargin - avatarSize) / 2) * avatarCollapseAnimateOffset
//                        this.translationY =
//                            ((binding.animToolbar.height - verticalToolbarAvatarMargin - avatarSize) / 2) * avatarCollapseAnimateOffset
//                    }
//                    else -> this.layoutParams.also {
//                        if (it.height != EXPAND_AVATAR_SIZE.toInt()) {
//                            it.height = EXPAND_AVATAR_SIZE.toInt()
//                            it.width = EXPAND_AVATAR_SIZE.toInt()
//                            this.layoutParams = it
//                        }
//                        translationX = 0f
//                    }
//                }
//            }
//        }
//    }
//
//    companion object {
//        const val SWITCH_BOUND = 0.8f
//        const val TO_EXPANDED = 0
//        const val TO_COLLAPSED = 1
//        const val WAIT_FOR_SWITCH = 0
//        const val SWITCHED = 1
//    }

    override fun setupViews() {
        getData()
    }

    private fun getData() {
        viewLifecycleOwner.lifecycleScope.launch {
            viewModel.fetchCharacterId(args.id).collect {
                when (it) {
                    is Resource.Loading -> {
                        Log.e("loo", "olo")
                    }
                    is Resource.Error -> {
                        Log.e("tag", "Error Character ${it.message.toString()}")
                    }
                    is Resource.Success -> {
                        binding.tvtName.text = it.data?.name
                        it.data?.let { it1 -> binding.image.setImage(it1.image) }
                    }
                }
            }
        }
    }
}

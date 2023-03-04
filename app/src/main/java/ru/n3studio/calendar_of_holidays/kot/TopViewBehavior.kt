package ru.n3studio.calendar_of_holidays.kot

import android.content.Context
import android.util.AttributeSet
import android.view.View
import android.view.animation.AccelerateInterpolator
import android.view.animation.DecelerateInterpolator
import android.view.animation.Interpolator
import android.view.animation.LinearInterpolator
import android.widget.ImageView
import android.widget.LinearLayout
import android.widget.TextView
import com.behaviorule.arturdumchev.library.*
import com.google.android.material.appbar.AppBarLayout
import com.google.android.material.appbar.CollapsingToolbarLayout
import ru.n3studio.calendar_of_holidays.Fragments.HomeFragment
import ru.n3studio.calendar_of_holidays.R


/**
 * @author arturdumchev on 13/10/2018.
 */
class TopInfoBehavior(
        context: Context?,
        attrs: AttributeSet?
) : BehaviorByRules(context, attrs) {

    override fun calcAppbarHeight(child: View): Int = with(child) {
        return (height + pixels(R.dimen.toolbar_height)).toInt()
    }
    override fun View.provideAppbar(): AppBarLayout = findViewById(R.id.ablAppbar)
    override fun View.provideCollapsingToolbar(): CollapsingToolbarLayout = findViewById(R.id.ctlToolbar)
    override fun canUpdateHeight(progress: Float): Boolean = progress >= GONE_VIEW_THRESHOLD

    override fun View.setUpViews(): List<RuledView> {
        val appearedUntil = 0.1f
        return listOf(
//                RuledView(
//                        findViewById(R.id.iTopDetails),
//                        BRuleYOffset(
//                                min = pixels(R.dimen.zero),
//                                max = pixels(R.dimen.toolbar_height)
//                        )
//                ),
                RuledView(
                        findViewById<LinearLayout>(R.id.tvTopDetails),
                        BRuleXOffset(
                                min = pixels(R.dimen.zero), max = pixels(R.dimen.big_margin),
                                interpolator = ReverseInterpolator(AccelerateInterpolator())
                        ),
                        BRuleYOffset(
                                min = -((findViewById<TextView>(R.id.textView2)).y
                                        -(findViewById<TextView>(R.id.tvCollapsedTop)).y+95), max = 0f,
                                interpolator =  DecelerateInterpolator(1f)
                        ),

                        BRuleAppear(appearedUntil),
                        BRuleAlpha(min = 0.2f, max = 1f).workInRange(from = appearedUntil, to = 1f),
                        BRuleScale(min = 0.6f, max = 1f)
                ),
//                RuledView(
//                        textView2,
//                        BRuleXOffset(
//                                min = 0f, max = pixels(R.dimen.big_margin),
//                                interpolator = ReverseInterpolator(AccelerateInterpolator())
//                        ),
//                        BRuleYOffset(
//                                min = pixels(R.dimen.zero), max = pixels(R.dimen.dialog_padding),
//                                interpolator = ReverseInterpolator(LinearInterpolator())
//                        ),
//                        BRuleAppear(appearedUntil),
//                        BRuleAlpha(min = 0.6f, max = 1f).workInRange(from = appearedUntil, to = 1f),
//                        BRuleScale(min = 0.8f, max = 1f)
//                ),
                RuledView(
                        findViewById(R.id.tvCollapsedTop),
                        BRuleAppear(appearedUntil, true)
                ),
                RuledView(
                        findViewById(R.id.imageView),
                        BRuleAppear(visibleUntil = GONE_VIEW_THRESHOLD, animationDuration = 100L)
                ),
                RuledView(
                        findViewById(R.id.constraintLayout2),
                        BRuleAppear(visibleUntil = GONE_VIEW_THRESHOLD, animationDuration = 100L)
                ),
                RuledView(
                        findViewById(R.id.textView3),
                        BRuleAppear(visibleUntil = GONE_VIEW_THRESHOLD, animationDuration = 100L)
                ),
                RuledView(
                        findViewById(R.id.imageView2),
                        BRuleAppear(visibleUntil = GONE_VIEW_THRESHOLD, animationDuration = 100L)
                ),
                RuledView(
                        findViewById(R.id.txt),
                        BRuleAppear(visibleUntil = GONE_VIEW_THRESHOLD, animationDuration = 100L)
                ),
//
                imagesRuleFunc(findViewById(R.id.sky_moon), LinearInterpolator()),
//                imagesRuleFunc2(textView2, LinearInterpolator())
//                imagesRuleFunc(ivTop2, DecelerateInterpolator(0.7f)),
//                imagesRuleFunc(ivTop3, DecelerateInterpolator()),
//                imagesRuleFunc(ivTop4, DecelerateInterpolator())
        )
    }

    private fun View.imagesRuleFunc(view: ImageView, interpolator: Interpolator) = RuledView(
            view,
//            BRuleYOffset(
//                    min = -(ivTop3.y - tvCollapsedTop.y),
//                    max = 0f,
//                    interpolator = DecelerateInterpolator(1.5f)
//            ),
            BRuleXOffset(
                    min = 0f,
                    max = findViewById<TextView>(R.id.tvCollapsedTop).width.toFloat() + pixels(R.dimen.huge_margin),
                    interpolator = ReverseInterpolator(interpolator)
            )
    )

    companion object {
        const val GONE_VIEW_THRESHOLD = 0.9f
    }
}

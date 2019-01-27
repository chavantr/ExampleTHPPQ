package com.passions.thehinduquestions

import android.content.Context
import android.os.Bundle
import android.support.v4.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.facebook.ads.AdSize
import com.facebook.ads.AdView
import kotlinx.android.synthetic.main.fragment_home.view.*
import java.util.*


class HomeFragment : Fragment() {


    private var adView: AdView? = null

    private var quoteOfTheDay = HashMap<Int, String>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        quoteOfTheDay.put(1, "Your personal life, your professional life, and your creative life are all intertwined. I went through a few very difficult years where I felt like a failure. But it was actually really important for me to go through that. Struggle, for me, is the most inspirational thing in the world at the end of the day - as long as you treat it that way. Skylar Grey\n")

        quoteOfTheDay.put(2, "When you wake up each morning, you can choose to be happy or choose to be sad. Unless some terrible catastrophe has occurred the night before, it is pretty much up to you. Tomorrow morning, when the sun shines through your window, choose to make it a happy day. \nLynda Resnick\n")

        quoteOfTheDay.put(3, "Many times, the decisions we make affect and hurt your closest friends and family the most. I have a lot of regrets in that regard. But God has forgiven me, which I am very thankful for. It has enabled me to forgive myself and move forward one day at a time. \nLex Luger\n")

        quoteOfTheDay.put(4, "Motherhood is a great honor and privilege, yet it is also synonymous with servanthood. Every day women are called upon to selflessly meet the needs of their families. Whether they are awake at night nursing a baby, spending their time and money on less-than-grateful teenagers, or preparing meals, moms continuously put others before themselves. \nCharles Stanley\n")

        quoteOfTheDay.put(5, "While you're going through this process of trying to find the satisfaction in your work, pretend you feel satisfied. Tell yourself you had a good day. Walk through the corridors with a smile rather than a scowl. Your positive energy will radiate. If you act like you're having fun, you'll find you are having fun. \nJean Chatzky\n")

        quoteOfTheDay.put(6, "It's just a whole different thing, and it's just that my life has been a blessing, and I thank god every day for the gifts that he has given me and for my daughter and to be able to watch her grow and be a part of her joys and her excitement and what she wants to do in life. \nTeena Marie\n")

        quoteOfTheDay.put(7, "There's a time in your life where you're not quite sure where you are. You think everything's perfect, but it's not perfect... Then one day you wake up and you can't quite picture yourself in the situation you're in. But the secret is, if you can picture yourself doing anything in life, you can do it. \nTom DeLonge\n")

    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {

        val view = inflater.inflate(R.layout.fragment_home, container, false)

        adView = AdView(context, "704548526584810_704549499918046", AdSize.BANNER_HEIGHT_50)

        view.banner_container.addView(adView)

        adView!!.loadAd();

        val calendar = Calendar.getInstance()
        val day = calendar.get(Calendar.DAY_OF_WEEK)

        when (day) {

            Calendar.SUNDAY -> {


                view.lblQuote.text = quoteOfTheDay[Calendar.SUNDAY]
            }

            Calendar.MONDAY -> {
                view.lblQuote.text = quoteOfTheDay[Calendar.MONDAY]
            }

            Calendar.TUESDAY -> {
                view.lblQuote.text = quoteOfTheDay[Calendar.TUESDAY]
            }

            Calendar.WEDNESDAY -> {
                view.lblQuote.text = quoteOfTheDay[Calendar.WEDNESDAY]
            }

            Calendar.THURSDAY -> {
                view.lblQuote.text = quoteOfTheDay[Calendar.THURSDAY]
            }

            Calendar.FRIDAY -> {
                view.lblQuote.text = quoteOfTheDay[Calendar.FRIDAY]
            }

            Calendar.SATURDAY -> {
                view.lblQuote.text = quoteOfTheDay[Calendar.SATURDAY]
            }

        }

        return view
    }


    override fun onAttach(context: Context) {
        super.onAttach(context)

    }

    override fun onDetach() {
        super.onDetach()

    }


    companion object {

        @JvmStatic
        fun newInstance(param1: String, param2: String) =
                HomeFragment().apply {
                    arguments = Bundle().apply {

                    }
                }
    }
}

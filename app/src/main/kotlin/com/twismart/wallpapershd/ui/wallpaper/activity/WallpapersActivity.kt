package com.twismart.wallpapershd.ui.wallpaper.activity

import android.content.Intent
import android.support.v4.view.ViewPager
import com.twismart.wallpapershd.R
import com.twismart.wallpapershd.data.model.Wallpaper
import com.twismart.wallpapershd.ui.wallpaper.fragment.WallpaperFragment
import com.twismart.wallpapershd.utils.Constants
import com.twismart.wallpapershd.utils.debug
import android.content.Context
import android.os.Bundle
import hugo.weaving.DebugLog
import kotlinx.android.synthetic.main.activity_wallpaper.*
import javax.inject.Inject

class WallpapersActivity : com.twismart.wallpapershd.ui.base.BaseActivity(), WallpapersContract.View, WallpaperFragment.OnWallpaperFragmentListener {

    companion object {
        fun newIntent(context: Context, wallpaperList: List<Wallpaper>, wallpaperToShow: Int): Intent {
            val newIntent = Intent(context, WallpapersActivity::class.java)
            newIntent.putParcelableArrayListExtra(Constants.WALLPAPERS_LIST, ArrayList(wallpaperList))
            newIntent.putExtra(Constants.WALLPAPER_TO_SHOW, wallpaperToShow)
            return newIntent
        }
    }

    var wallpapersList: ArrayList<com.twismart.wallpapershd.data.model.Wallpaper>? = null

    @Inject lateinit var mPresenter: WallpapersContract.Presenter<WallpapersContract.View>

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(com.twismart.wallpapershd.R.layout.activity_wallpaper)
        activityComponent.inject(this)
        mPresenter.attachView(this)
        setUp()
    }

    @DebugLog
    override fun setUp() {
        debug("setUp")
        setSupportActionBar(toolbar)

        supportActionBar?.setDisplayHomeAsUpEnabled(true)

        wallpapersList = intent?.getParcelableArrayListExtra<Wallpaper>(Constants.WALLPAPERS_LIST)
        mViewPager.adapter = WallpapersPagerAdapter(supportFragmentManager, applicationContext, wallpapersList)
        var currentItem = intent.getIntExtra(Constants.WALLPAPER_TO_SHOW, 0)
        //set and show the wallpaper selected
        debug("current $currentItem")
        mViewPager.currentItem = currentItem
        mViewPager.addOnPageChangeListener(object : ViewPager.OnPageChangeListener{
            override fun onPageScrollStateChanged(p0: Int) {}
            override fun onPageScrolled(p0: Int, p1: Float, p2: Int) {}
            override fun onPageSelected(position: Int) {
                debug("OnPageSelected $position")
                currentItem = position
                val wallpaperFragment = mViewPager.adapter.instantiateItem(mViewPager, position) as WallpaperFragment
                debug("wallpaperFragment url ${wallpaperFragment.mWallpaper.urlImage}")
                mPresenter.checkIfWallpaperIsFavorite(wallpaperFragment.mWallpaper.id, position)
            }
        })
        //the checking should be after the post once the fragment is instanced and ready to be used
        mViewPager.post {
            debug("mViewPager post")
            mPresenter.checkIfWallpaperIsFavorite(intent.getParcelableArrayListExtra<Wallpaper>(Constants.WALLPAPERS_LIST)[currentItem].id, currentItem)
        }
    }

    //My View methods
    override fun wallpaperIsInFavorites(positionFragment: Int) {
        val wallpaperFragment = mViewPager.adapter.instantiateItem(mViewPager, positionFragment) as WallpaperFragment
        wallpaperFragment.wallpaperIsInFavorites()
    }

    override fun wallpaperIsNotInFavorites(positionFragment: Int) {
        val wallpaperFragment = mViewPager.adapter.instantiateItem(mViewPager, positionFragment) as WallpaperFragment
        wallpaperFragment.wallpaperIsNotInFavorites()
    }

    //WallpaperFragment Listener
    override fun onClickFavorite(wallpaperToFavorites: Wallpaper, positionFragment: Int) {
        mPresenter.addWallpaperToFavorites(wallpaperToFavorites, positionFragment)
    }

    override fun onClickUnFavorite(id: String, positionFragment: Int) {
        mPresenter.deleteWallpaperFromFavorites(id, positionFragment)
    }


    //Menu methods
    override fun onCreateOptionsMenu(menu: android.view.Menu): Boolean {
        menuInflater.inflate(com.twismart.wallpapershd.R.menu.menu_wallpapers, menu)
        return super.onCreateOptionsMenu(menu)
    }

    override fun onOptionsItemSelected(item: android.view.MenuItem): Boolean {
        when (item.itemId) {
            android.R.id.home -> finish()
            R.id.menu_set_wallpaper -> {
                val url = wallpapersList!![mViewPager!!.currentItem].urlImage
                debug("onOptionsItemSelected: urlImage $url")
                mPresenter.setWallpaperFromUrl(url)
            }
        }
        return super.onOptionsItemSelected(item)
    }

    override fun onDestroy() {
        super.onDestroy()
        mPresenter.dettachView()
        debug("activity onDestroy")
    }
}

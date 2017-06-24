/*
 * Copyright (C) 2017 Sneyder Angulo.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

import com.twismart.wallpapershd.data.model.Wallpaper

class FakeWallpapersDataSource {
    companion object {
        fun makeListWallpapers(number: Int): ArrayList<Wallpaper> {
            val wallpapers = ArrayList<Wallpaper>()
            (0..number).forEach {
                wallpapers.add(makeWallpaper(it.toString()))
            }
            return wallpapers
        }

        fun makeWallpaper(id: String) = Wallpaper(id, "url", "", "1280", "720", "", "10")
    }
}
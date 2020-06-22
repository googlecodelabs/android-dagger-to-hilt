/*
 * Copyright (C) 2020 The Android Open Source Project
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

package com.example.android.dagger.main

import com.example.android.dagger.user.UserDataRepository
import com.squareup.inject.assisted.Assisted
import com.squareup.inject.assisted.AssistedInject
import com.squareup.inject.assisted.dagger2.AssistedModule
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ActivityComponent

/**
 * MainViewModel is the ViewModel that [MainActivity] uses to
 * obtain information of what to show on the screen.
 *
 * @Inject tells Dagger how to provide instances of this type. Dagger also knows
 * that UserDataRepository is a dependency.
 */
class MainViewModel @AssistedInject constructor(
    @Assisted private val userDataRepository: UserDataRepository
) {

    @AssistedInject.Factory
    interface Factory {
        fun create(userDataRepository: UserDataRepository): MainViewModel
    }

    val welcomeText: String
        get() = "Hello ${userDataRepository.username}!"

    val notificationsText: String
        get() = "You have ${userDataRepository.unreadNotifications} unread notifications"
}

@InstallIn(ActivityComponent::class)
@AssistedModule
@Module(includes = [AssistedInject_AssistedInjectModule::class])
// Needed until AssistedInject is incorporated into Dagger
interface AssistedInjectModule {}

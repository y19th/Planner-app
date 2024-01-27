package com.example.planner_app.di

import android.content.Context
import dagger.BindsInstance
import dagger.Component
import dagger.Module

@Component
interface MainComponent {

    @Component.Builder
    interface Builder {
        @BindsInstance
        fun context(context: Context): Builder

        fun build(): MainComponent
    }
}


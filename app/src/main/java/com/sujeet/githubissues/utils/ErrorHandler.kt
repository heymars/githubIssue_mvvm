package com.sujeet.githubissues.utils

import timber.log.Timber

fun defaultErrorHandler(): (Throwable) -> Unit = { e -> Timber.e(e) }
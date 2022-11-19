/*
 * Copyright 2022 The Android Open Source Project
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     https://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package com.google.samples.apps.nowinandroid.core.model.data

import com.google.samples.apps.nowinandroid.core.model.data.NewsResourceType.Unknown
import kotlin.random.Random
import kotlinx.datetime.Instant

fun Random.nextFakeNewsResource(
    id: String = nextLong().toString(),
    title: String = "News resource $id",
    content: String = "",
    url: String = "https://example.org/news/$id",
    headerImageUrl: String? = "https://example.org/news/$id.png",
    publishDate: Instant = Instant.fromEpochMilliseconds(0),
    type: NewsResourceType = Unknown,
    authors: List<Author> = emptyList(),
    topics: List<Topic> = emptyList(),
): NewsResource = NewsResource(
    id = id,
    title = title,
    content = content,
    url = url,
    headerImageUrl = headerImageUrl,
    publishDate = publishDate,
    type = type,
    authors = authors,
    topics = topics,
)

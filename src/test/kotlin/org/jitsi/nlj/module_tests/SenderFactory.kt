/*
 * Copyright @ 2018 - present 8x8, Inc.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package org.jitsi.nlj.module_tests

import org.jitsi.nlj.RtpSender
import org.jitsi.nlj.RtpSenderImpl
import org.jitsi.nlj.rtcp.RtcpEventNotifier
import org.jitsi.test_utils.SrtpData
import java.util.Random
import java.util.concurrent.ExecutorService
import java.util.concurrent.ScheduledExecutorService

class SenderFactory {
    companion object {
        fun createSender(
            executor: ExecutorService,
            backgroundExecutor: ScheduledExecutorService,
            srtpData: SrtpData
        ): RtpSender {
            val sender = RtpSenderImpl(
                Random().nextLong().toString(),
                null,
                RtcpEventNotifier(),
                executor,
                backgroundExecutor
            )
            sender.setSrtpTransformer(SrtpTransformerFactory.createSrtpTransformer(srtpData))
            sender.setSrtcpTransformer(SrtpTransformerFactory.createSrtcpTransformer(srtpData))

            return sender
        }
    }
}
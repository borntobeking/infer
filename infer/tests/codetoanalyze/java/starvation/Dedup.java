/*
 * Copyright (c) 2018-present, Facebook, Inc.
 *
 * This source code is licensed under the MIT license found in the
 * LICENSE file in the root directory of this source tree.
 */

import java.util.concurrent.Future;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.CountDownLatch;
import android.support.annotation.UiThread;
import java.io.IOException;

class Dedup {
  CountDownLatch latch;
  Future future;

  // only one report should be seen
  @UiThread
  void onUiThreadBad() throws InterruptedException, ExecutionException {
    callMethodWithMultipleBlocksBad();
  }

  // three reports are expected
  @UiThread
  void callMethodWithMultipleBlocksBad() throws InterruptedException, ExecutionException {
    future.get();
    latch.await();
    future.get();
  }
}

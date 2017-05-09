/*
 * Copyright (C) 2017 Baidu, Inc. All Rights Reserved.
 */
package com.fast.framework.view.coverflow;

import android.view.View;

public interface IViewObserver {
	/**
	 * @param v View which is getting removed
	 * @param position View position in adapter
	 */
	void onViewRemovedFromParent(View v, int position);
}

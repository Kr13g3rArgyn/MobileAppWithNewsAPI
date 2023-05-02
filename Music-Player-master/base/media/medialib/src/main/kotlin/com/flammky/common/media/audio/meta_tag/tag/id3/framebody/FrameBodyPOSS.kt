/*
 *  MusicTag Copyright (C)2003,2004
 *
 *  This library is free software; you can redistribute it and/or modify it under the terms of the GNU Lesser
 *  General Public  License as published by the Free Software Foundation; either version 2.1 of the License,
 *  or (at your option) any later version.
 *
 *  This library is distributed in the hope that it will be useful, but WITHOUT ANY WARRANTY; without even
 *  the implied warranty of MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.
 *  See the GNU Lesser General Public License for more details.
 *
 *  You should have received a copy of the GNU Lesser General Public License along with this library; if not,
 *  you can get a copy from http://www.opensource.org/licenses/lgpl-license.php or write to the Free Software
 *  Foundation, Inc., 51 Franklin Street, Fifth Floor, Boston, MA 02110-1301 USA
 */
package com.flammky.musicplayer.common.media.audio.meta_tag.tag.id3.framebody

import com.flammky.musicplayer.common.media.audio.meta_tag.tag.datatype.DataTypes
import com.flammky.musicplayer.common.media.audio.meta_tag.tag.datatype.NumberHashMap
import com.flammky.musicplayer.common.media.audio.meta_tag.tag.datatype.NumberVariableLength
import com.flammky.musicplayer.common.media.audio.meta_tag.tag.id3.ID3v24Frames
import com.flammky.musicplayer.common.media.audio.meta_tag.tag.id3.valuepair.EventTimingTimestampTypes
import java.nio.ByteBuffer

/**
 * Position synchronisation frame.
 *
 *
 * This frame delivers information to the listener of how far into the
 * audio stream he picked up; in effect, it states the time offset of
 * the first frame in the stream. The frame layout is:
 *
 * Head for 'Position synchronisation', ID:
 * Time stamp format $xx
 * Position          $xx (xx ...)
 *
 * Where time stamp format is:
 *
 * $01 Absolute time, 32 bit sized, using MPEG frames as unit
 * $02 Absolute time, 32 bit sized, using milliseconds as unit
 *
 * and position is where in the audio the listener starts to receive,
 * i.e. the beginning of the next frame. If this frame is used in the
 * beginning of a file the value is always 0. There may only be one
 * "POSS" frame in each tag.
 *
 *
 *
 * For more details, please refer to the ID3 specifications:
 *
 *  * [ID3 v2.3.0 Spec](http://www.id3.org/id3v2.3.0.txt)
 *
 *
 * @author : Paul Taylor
 * @author : Eric Farng
 * @version $Id$
 */
class FrameBodyPOSS : AbstractID3v2FrameBody, ID3v24FrameBody, ID3v23FrameBody {

	override val identifier: String?
		get() = ID3v24Frames.FRAME_ID_POSITION_SYNC

	/**
	 * Creates a new FrameBodyPOSS datatype.
	 */
	constructor() {
		//        this.setObject(ObjectNumberHashMap.OBJ_TIME_STAMP_FORMAT, new Byte((byte) 0));
		//        this.setObject("Position", new Long(0));
	}

	constructor(body: FrameBodyPOSS) : super(body)

	/**
	 * Creates a new FrameBodyPOSS datatype.
	 *
	 * @param timeStampFormat
	 * @param position
	 */
	constructor(timeStampFormat: Byte, position: Long) {
		setObjectValue(DataTypes.OBJ_TIME_STAMP_FORMAT, timeStampFormat)
		setObjectValue(DataTypes.OBJ_POSITION, position)
	}

	/**
	 * Creates a new FrameBodyPOSS datatype.
	 *
	 * @param byteBuffer
	 * @param frameSize
	 * @throws InvalidTagException if unable to create framebody from buffer
	 */
	constructor(byteBuffer: ByteBuffer, frameSize: Int) : super(byteBuffer, frameSize)

	/**
	 *
	 */
	override fun setupObjectList() {
		objectList.add(
			NumberHashMap(
				DataTypes.OBJ_TIME_STAMP_FORMAT,
				this,
				EventTimingTimestampTypes.TIMESTAMP_KEY_FIELD_SIZE
			)
		)
		objectList.add(NumberVariableLength(DataTypes.OBJ_POSITION, this, 1))
	}
}

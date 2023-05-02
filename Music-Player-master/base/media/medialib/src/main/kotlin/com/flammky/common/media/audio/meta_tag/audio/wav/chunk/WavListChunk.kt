/*
 * Entagged Audio Tag library
 * Copyright (c) 2003-2005 Rapha�l Slinckx <raphael@slinckx.net>
 *
 * This library is free software; you can redistribute it and/or
 * modify it under the terms of the GNU Lesser General Public
 * License as published by the Free Software Foundation; either
 * version 2.1 of the License, or (at your option) any later version.
 *
 * This library is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the GNU
 * Lesser General Public License for more details.
 *
 * You should have received a copy of the GNU Lesser General Public
 * License along with this library; if not, write to the Free Software
 * Foundation, Inc., 51 Franklin St, Fifth Floor, Boston, MA  02110-1301  USA
 */
package com.flammky.musicplayer.common.media.audio.meta_tag.audio.wav.chunk

import com.flammky.musicplayer.common.media.audio.meta_tag.audio.generic.Utils.readFourBytesAsChars
import com.flammky.musicplayer.common.media.audio.meta_tag.audio.iff.Chunk
import com.flammky.musicplayer.common.media.audio.meta_tag.audio.iff.ChunkHeader
import com.flammky.musicplayer.common.media.audio.meta_tag.audio.wav.WavChunkType
import com.flammky.musicplayer.common.media.audio.meta_tag.tag.wav.WavTag
import java.io.IOException
import java.nio.ByteBuffer
import java.util.logging.Logger

/**
 * Reads a list chunk, only interested in it if contains INFO chunk as this contains basic metadata
 */
class WavListChunk(
	private val loggingName: String,
	chunkData: ByteBuffer?,
	chunkHeader: ChunkHeader?,
	private val tag: WavTag
) : Chunk(
	chunkData!!, chunkHeader!!
) {
	private val isValid = false

	/**
	 *
	 * @return true if successfully read chunks
	 *
	 * @throws IOException
	 */
	@Throws(IOException::class)
	override fun readChunk(): Boolean {
		var result = false
		val subIdentifier = readFourBytesAsChars(chunkData)
		if (subIdentifier == WavChunkType.INFO.code) {
			val chunk = WavInfoChunk(
				tag, loggingName
			)
			result = chunk.readChunks(chunkData)

			//This is the start of the enclosing LIST element
			tag.infoTag!!.setStartLocationInFile(chunkHeader.startLocationInFile)
			tag.infoTag!!.setEndLocationInFile(chunkHeader.startLocationInFile + ChunkHeader.CHUNK_HEADER_SIZE + chunkHeader.size)
			tag.isExistingInfoTag = true
		} else {
			logger.severe(
				"LIST chunk does not contain INFO instead contains $subIdentifier so skipping"
			)
			result = true
		}
		return result
	}

	override fun toString(): String {
		var out = "RIFF-WAVE Header:\n"
		out += "Is valid?: $isValid"
		return out
	}

	companion object {
		var logger = Logger.getLogger("org.jaudiotagger.audio.wav.chunk.WavListChunk")
	}
}

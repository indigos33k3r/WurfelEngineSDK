/*
 * If this software is used for a game the official „Wurfel Engine“ logo or its name must be visible in an intro screen or main menu.
 *
 * Copyright 2015 Benedikt Vogler.
 * All rights reserved.
 *
 * Redistribution and use in source and binary forms, with or without
 * modification, are permitted provided that the following conditions are met:
 *
 * * Redistributions of source code must retain the above copyright notice, 
 *   this list of conditions and the following disclaimer.
 * * Redistributions in binary form must reproduce the above copyright notice, 
 *   this list of conditions and the following disclaimer in the documentation 
 *   and/or other materials provided with the distribution.
 * * Neither the name of Benedikt Vogler nor the names of its contributors 
 *   may be used to endorse or promote products derived from this software without specific
 *   prior written permission.
 *
 * THIS SOFTWARE IS PROVIDED BY THE COPYRIGHT HOLDERS AND CONTRIBUTORS "AS IS"
 * AND ANY EXPRESS OR IMPLIED WARRANTIES, INCLUDING, BUT NOT LIMITED TO, THE
 * IMPLIED WARRANTIES OF MERCHANTABILITY AND FITNESS FOR A PARTICULAR PURPOSE
 * ARE DISCLAIMED. IN NO EVENT SHALL THE COPYRIGHT HOLDER OR CONTRIBUTORS BE
 * LIABLE FOR ANY DIRECT, INDIRECT, INCIDENTAL, SPECIAL, EXEMPLARY, OR
 * CONSEQUENTIAL DAMAGES (INCLUDING, BUT NOT LIMITED TO, PROCUREMENT OF
 * SUBSTITUTE GOODS OR SERVICES; LOSS OF USE, DATA, OR PROFITS; OR BUSINESS
 * INTERRUPTION) HOWEVER CAUSED AND ON ANY THEORY OF LIABILITY, WHETHER IN
 * CONTRACT, STRICT LIABILITY, OR TORT (INCLUDING NEGLIGENCE OR OTHERWISE)
 * ARISING IN ANY WAY OUT OF THE USE OF THIS SOFTWARE, EVEN IF ADVISED OF THE
 * POSSIBILITY OF SUCH DAMAGE.
 */
package com.bombinggames.wurfelengine.core.cvar;

import java.io.File;

import com.bombinggames.wurfelengine.core.map.CustomMapCVarRegistration;
import com.bombinggames.wurfelengine.core.map.Map;

/**
 *
 * @author Benedikt Vogler
 */
public class CVarSystemMap extends AbstractCVarSystem {
	
	private static CustomMapCVarRegistration customRegistration;

	/**
	 *
	 * @param customMapRegistration
	 */
	public static void setCustomMapCVarRegistration(CustomMapCVarRegistration customMapRegistration) {
		customRegistration = customMapRegistration;
	}

	private CVarSystemSave saveSystem;
	
	/**
	 *
	 * @param path
	 */
	public CVarSystemMap(File path) {
		super(path);
		register(new IntCVar(Map.MAPVERSION), "MapVersion", CVarFlags.INSTANTSAVE);
		register(new IntCVar(1), "groundBlockID");
		register(new IntCVar(10), "chunkBlocksX");
		register(new IntCVar(40), "chunkBlocksY");
		register(new IntCVar(10), "chunkBlocksZ");
		register(new StringCVar(""), "mapname");
		register(new StringCVar(""), "description");
		register(new IntCVar(-1), "currentSaveSlot", CVarFlags.VOlATILE);
		
		if (customRegistration != null) {
			customRegistration.register(this);
		}
	}

	/**
	 *
	 * @return
	 */
	public CVarSystemSave getSaveCVars() {
		return saveSystem;
	}

	/**
	 * Set the save system for this map.
	 * @param saveSystem 
	 */
	public void setSaveCVars(CVarSystemSave saveSystem) {
		this.saveSystem = saveSystem;
	}
	
}

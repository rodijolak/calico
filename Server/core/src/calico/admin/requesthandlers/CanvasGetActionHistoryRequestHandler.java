/*******************************************************************************
 * Copyright (c) 2013, Regents of the University of California
 * All rights reserved.
 * 
 * Redistribution and use in source and binary forms, with or without modification, are permitted provided
 * that the following conditions are met:
 * 
 * Redistributions of source code must retain the above copyright notice, this list of conditions
 * and the following disclaimer.
 * 
 * Redistributions in binary form must reproduce the above copyright notice, this list of conditions
 * and the following disclaimer in the documentation and/or other materials provided with the
 * distribution.
 * 
 * None of the name of the Regents of the University of California, or the names of its
 * contributors may be used to endorse or promote products derived from this software without specific
 * prior written permission.
 * 
 * THIS SOFTWARE IS PROVIDED BY THE COPYRIGHT HOLDERS AND CONTRIBUTORS "AS IS" AND ANY EXPRESS OR IMPLIED
 * WARRANTIES, INCLUDING, BUT NOT LIMITED TO, THE IMPLIED WARRANTIES OF MERCHANTABILITY AND FITNESS FOR A
 * PARTICULAR PURPOSE ARE DISCLAIMED. IN NO EVENT SHALL THE COPYRIGHT OWNER OR CONTRIBUTORS BE LIABLE FOR
 * ANY DIRECT, INDIRECT, INCIDENTAL, SPECIAL, EXEMPLARY, OR CONSEQUENTIAL DAMAGES (INCLUDING, BUT NOT
 * LIMITED TO, PROCUREMENT OF SUBSTITUTE GOODS OR SERVICES; LOSS OF USE, DATA, OR PROFITS; OR BUSINESS
 * INTERRUPTION) HOWEVER CAUSED AND ON ANY THEORY OF LIABILITY, WHETHER IN CONTRACT, STRICT LIABILITY, OR
 * TORT (INCLUDING NEGLIGENCE OR OTHERWISE) ARISING IN ANY WAY OUT OF THE USE OF THIS SOFTWARE, EVEN IF
 * ADVISED OF THE POSSIBILITY OF SUCH DAMAGE.
 ******************************************************************************/
package calico.admin.requesthandlers;


import java.io.*;
import java.net.*;
import java.util.*;

import org.apache.http.*;
import org.apache.http.entity.*;
import org.apache.http.protocol.*;
import org.apache.http.util.*;

import org.json.me.*;

import calico.*;
import calico.admin.*;
import calico.admin.exceptions.*;
import calico.clients.*;
import calico.controllers.CCanvasController;

import it.unimi.dsi.fastutil.longs.*;

public class CanvasGetActionHistoryRequestHandler extends AdminBasicRequestHandler
{
	
	//  /session/canvases/get/<uuid>
	
	protected void handleRequest(final HttpRequest request, final HttpResponse response) throws HttpException, IOException, JSONException, CalicoAPIErrorException
	{
		response.setStatusCode(HttpStatus.SC_OK);
		
		URL requestURL = uri2url(request.getRequestLine().getUri());
		
		Properties params = urlQuery2Properties(requestURL);
		
		final long uuid = Long.parseLong(params.getProperty("uuid","0"));
		
		if(uuid==0)
		{
			// ERROR
			throw new NotFoundException("The canvas you requested was not found.");
		}
		
		if(!CCanvasController.canvases.containsKey(uuid))
		{
			throw new NotFoundException("The canvas you requested was not found.");
		}
			
		
		JSONObject resp = new JSONObject();

//		resp.put("actionhistory", CCanvasController.canvases.get(uuid).getActionHistoryAsJSON() );
		
		
		StringEntity body = new StringEntity(resp.toString());
		body.setContentType(COptions.APIDefaultContentType);
		response.setEntity(body);
		
	}
	
	
}

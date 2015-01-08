/*
	
	Copyright 2014 Caspar Isemer, Eva Krueger and IDEAL Group Inc.(http://www.ideal-group.org), http://easyaccess.org
	
	Licensed under the Apache License, Version 2.0 (the "License");
	you may not use this file except in compliance with the License.
	You may obtain a copy of the License at
	
		http://www.apache.org/licenses/LICENSE-2.0
	
	Unless required by applicable law or agreed to in writing, software
	distributed under the License is distributed on an "AS IS" BASIS,
	WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
	See the License for the specific language governing permissions and
	limitations under the License. 
 */
package org.easyaccess;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.text.method.KeyListener;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.Toast;

import org.easyaccess.settings.SettingsMenu;
import org.easyaccess.simplemenus.CameraAppsMenu;
import org.easyaccess.simplemenus.SocialNetworksMenu;


public class Homescreen3Activity extends AbstractHomescreenActivity implements KeyListener {
	/**
	 * The HomeScreenActivity displays the options available in the app.
	 */


	/** Create the Main activity showing home screen #3 **/
	@Override
	public View onCreateView(final LayoutInflater inflater,
			final ViewGroup container, Bundle savedInstanceState) {
		View v = inflater.inflate(R.layout.homescreen3, container, false);
		this.view = v;

		// Launch respective easyaccess app, depending on which button is
		// pressed
		attachListenerToOpenExternalApp((Button) v.findViewById(R.id.btnFileBrowser), "com.rhmsoft.fm");
		attachListenerToOpenActivity((Button) v.findViewById(R.id.btnCameraApps), CameraAppsMenu.class);
		attachListenerToOpenActivity((Button) v.findViewById(R.id.btnSocialNetworks), SocialNetworksMenu.class);
		attachListenerToOpenActivity((Button) v.findViewById(R.id.btnSettings), SettingsMenu.class);

        /** Find UI elements **/
        Button btnMoreAccessibleApps = (Button) v.findViewById(R.id.btnMoreAccessibleApps);
        Button btnAllApps = (Button) v.findViewById(R.id.btnAllApps);

        /** If More Accessible Apps button is pressed, open browser with accessible apps page of Eyes-Free project **/
        btnMoreAccessibleApps.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                // Currently uses default browser; in future, given that Chrome will be best browser to ensure
                // accessibility features are built in, should open the page specifically in Chrome
                showMoreAccessibleApps();
            }
        });

        /** If All Apps button is pressed, open regular All Apps menu **/
        btnAllApps.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                // Open the regular Android menu that lists all the apps installed on the device
                // Currently cannot identify the right code that would do that
                showAllApps();
            }
        });

		/** Put most everything before here **/
		return v;
	}

    void showAllApps() {
        //Toast.makeText(getActivity(), "We do not know how to open the regular Android menu with all installed apps yet, unfortunately!", Toast.LENGTH_LONG).show();
        Intent intent = new Intent(Intent.ACTION_MAIN);
        intent.addCategory(Intent.CATEGORY_LAUNCHER);
    }

    void showMoreAccessibleApps() {
        Uri websiteUri = Uri.parse("http://eyes-free.googlecode.com/svn/trunk/documentation/android_access/apps.html");
        Intent intent = new Intent(Intent.ACTION_VIEW, websiteUri);
        startActivity(intent);
    }

    @Override
    void startSelectedActivity(View view) {
        switch (view.getId()) {
            case R.id.btnFileBrowser:
                launchOrDownloadFromFragment("com.rhmsoft.fm");
                break;
            case R.id.btnCameraApps:
                startNewActivity(CameraAppsMenu.class);
                break;
            case R.id.btnSocialNetworks:
                startNewActivity(SocialNetworksMenu.class);
                break;
            case R.id.btnSettings:
                startNewActivity(SettingsMenu.class);
                break;
            case R.id.btnMoreAccessibleApps:
                showMoreAccessibleApps();
                break;
            case R.id.btnAllApps:
                showAllApps();
                break;
        }
    }

}

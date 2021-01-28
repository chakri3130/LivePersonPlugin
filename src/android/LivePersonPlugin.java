package cordova.plugin.LivePersonPlugin;

import android.content.Context;
import android.content.Intent;

import com.liveperson.infra.BadArgumentException;
import com.liveperson.infra.CampaignInfo;
import com.liveperson.infra.ConversationViewParams;
import com.liveperson.infra.ICallback;
import com.liveperson.infra.InitLivePersonProperties;
import com.liveperson.infra.MonitoringInitParams;
import com.liveperson.infra.auth.LPAuthenticationParams;
import com.liveperson.infra.auth.LPAuthenticationType;
import com.liveperson.infra.callbacks.InitLivePersonCallBack;
import com.liveperson.infra.model.LPWelcomeMessage;
import com.liveperson.infra.model.MessageOption;
import com.liveperson.messaging.sdk.api.LivePerson;
import com.liveperson.messaging.sdk.api.callbacks.LogoutLivePersonCallback;
import com.liveperson.messaging.sdk.api.model.ConsumerProfile;
import com.liveperson.monitoring.model.EngagementDetails;
import com.liveperson.monitoring.model.LPMonitoringIdentity;
import com.liveperson.monitoring.sdk.MonitoringParams;
import com.liveperson.monitoring.sdk.api.LivepersonMonitoring;
import com.liveperson.monitoring.sdk.callbacks.EngagementCallback;
import com.liveperson.monitoring.sdk.callbacks.MonitoringErrorType;
import com.liveperson.monitoring.sdk.responses.LPEngagementResponse;

import cordova.plugin.LivePersonPlugin.ChatEntryPoint;

import org.apache.cordova.CallbackContext;
import org.apache.cordova.CordovaPlugin;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import org.json.JSONArray;
import org.json.JSONException;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * This class echoes a string called from JavaScript.
 */
public class LivePersonPlugin extends CordovaPlugin {

    private static final String BRAND_ID = "70387001";
    private static final String APP_ID = "io.ionic.starter"; //TODO set appId. It's the applicationId, which will be used for FCM.
    private static final String APP_INSTALLATION_ID = "81be1920-b6cb-450d-87eb-d21b9c90e62f";
    private static final String ISSUER = "";

    private ChatEntryPoint entryPoint = ChatEntryPoint.androidDefault; //TODO pass the correct entry point.

    @Override
    public boolean execute(String action, JSONArray args, CallbackContext callbackContext) throws JSONException {
        if (action.equals("ConnectToBot")) {
            String message = args.getString(0);
            this.ConnectToBot(message, callbackContext);
            return true;
        }
        return false;
    }

    private void coolMethod(String message, CallbackContext callbackContext) {
        if (message != null && message.length() > 0) {
            callbackContext.success(message);
        } else {
            callbackContext.error("Expected one non-empty string argument.");
        }
    }

    private void ConnectToBot(String message, CallbackContext callbackContext) {
        if (message != null && message.length() > 0) {
            callbackContext.success(message);

            initLpSDK();
            /*Context context = cordova.getActivity().getApplicationContext();
            Intent intent = new Intent(context, cordova.plugin.LivePersonPlugin.LivePersonChatRoom.class);
            this.cordova.getActivity().startActivity(intent);*/
        } else {
            callbackContext.error("Expected one non-empty string argument.");
        }
    }

    private void initLpSDK() {
        Context context = cordova.getActivity().getApplicationContext();
        MonitoringInitParams monitoringInitParams = new MonitoringInitParams(APP_INSTALLATION_ID);
        LivePerson.initialize(context, new InitLivePersonProperties(BRAND_ID, APP_ID, monitoringInitParams, new InitLivePersonCallBack() {
            @Override
            public void onInitSucceed() {

                LPMonitoringIdentity identity = new LPMonitoringIdentity("", ISSUER);
                JSONArray entryPoints = new JSONArray();
                for (String entryPoint : entryPoint.entryPoints()) {
                    entryPoints.put(entryPoint);
                }
                MonitoringParams params = new MonitoringParams("", entryPoints, new JSONArray());

                LivepersonMonitoring.getEngagement(context, Collections.singletonList(identity), params, new EngagementCallback() {
                    @Override
                    public void onSuccess(@NotNull LPEngagementResponse response) {
                        if (response.getEngagementDetailsList() != null) {
                            try {
                                EngagementDetails detail = response.getEngagementDetailsList().get(0);

                                CampaignInfo campaignInfo = new CampaignInfo(Long.parseLong(detail.getCampaignId()),
                                        Long.parseLong(detail.getEngagementId()), detail.getContextId(),
                                        response.getSessionId(), response.getVisitorId());

                                ConversationViewParams conversationViewParams = new ConversationViewParams()
                                        .setCampaignInfo(campaignInfo);

                                conversationViewParams.setLpWelcomeMessage(getWelcomeMessage(entryPoint));
                                setUserProfile();
                                LivePerson.showConversation(cordova.getActivity(), getLPAuthenticationParams(), conversationViewParams);
                            } catch (BadArgumentException e) {
                                //TODO add error handling
                            }
                        }
                    }

                    @Override
                    public void onError(@NotNull MonitoringErrorType monitoringErrorType, @Nullable Exception e) {
                        //TODO add error handling
                    }
                });
            }

            @Override
            public void onInitFailed(Exception e) {
                //TODO add error handling
            }
        }));
    }

    /**
     * Set the authentication details.
     *
     * @return the {@link LPAuthenticationParams} object.
     */
    private LPAuthenticationParams getLPAuthenticationParams() {
        return new LPAuthenticationParams(LPAuthenticationType.SIGN_UP);
    }

    private void setUserProfile() {
        //TODO set user profile below
        ConsumerProfile userProfile = new ConsumerProfile.Builder()
                .setFirstName("")
                .setLastName("")
                .setNickname("")
                .setPhoneNumber("")
                .setAvatarUrl("").build();
        LivePerson.setUserProfile(userProfile);
    }

    private LPWelcomeMessage getWelcomeMessage(ChatEntryPoint entryPoint) {
        LPWelcomeMessage lpWelcomeMessage = new LPWelcomeMessage(entryPoint.welcomeMessage());
        List<MessageOption> optionItems = new ArrayList<>(entryPoint.quickReplies());
        try {
            lpWelcomeMessage.setMessageOptions(optionItems);
        } catch (Exception e) {
            //TODO add error handling
        }
        lpWelcomeMessage.setNumberOfItemsPerRow(1);
        lpWelcomeMessage.setMessageFrequency(LPWelcomeMessage.MessageFrequency.EVERY_CONVERSATION);
        return lpWelcomeMessage;
    }

    public void registerLPPusher(String deviceToken) {
        LivePerson.registerLPPusher(BRAND_ID, APP_ID, deviceToken, getLPAuthenticationParams(), new ICallback<Void, Exception>() {
            @Override
            public void onSuccess(Void aVoid) {

            }

            @Override
            public void onError(Exception e) {
                //TODO add error handling
            }
        });
    }

    /**
     * Logout the SDK. Device will be unregistered from LP pusher.
     * All cached data will be deleted.
     */
    public void logout() {
        Context context = cordova.getActivity().getApplicationContext();
        LivePerson.logOut(context, BRAND_ID, APP_ID, new LogoutLivePersonCallback() {
            @Override
            public void onLogoutSucceed() {
                //TODO other tasks after logout
            }

            @Override
            public void onLogoutFailed() {
                //TODO add error handling
            }
        });
    }

    /**
     * Clear conversation history. (Only closed conversation can be cleared)
     */
    public void clearHistory() {
        LivePerson.clearHistory();
    }
}

package cordova.plugin.LivePersonPlugin;

import com.liveperson.infra.model.MessageOption;

import java.io.Serializable;
import java.util.Arrays;
import java.util.List;

public enum ChatEntryPoint implements Serializable {

    androidDefault {
        @Override
        List<String> entryPoints() {
            return Arrays.asList("android-default", "prod", "us", "es");
        }

        @Override
        String welcomeMessage() {
            return "Hi, I'm BELLA, your automated health assistant. I'll be guiding you through your at-home COVID-19 test.";
        }

        @Override
        List<MessageOption> quickReplies() {
            //noinspection ArraysAsListWithZeroOrOneArgument
            return Arrays.asList(new MessageOption("Start", "Start"));
        }
    },
    faq {
        @Override
        List<String> entryPoints() {
            return Arrays.asList("android-faq", "prod", "us", "es");
        }

        @Override
        String welcomeMessage() {
            return "Hi, I'm BELLA, your automated health assistant. I'll be guiding you through your at-home COVID-19 test.";
        }

        @Override
        List<MessageOption> quickReplies() {
            //noinspection ArraysAsListWithZeroOrOneArgument
            return Arrays.asList(new MessageOption("Start", "Start"));
        }
    },
    testingStep1 {
        @Override
        List<String> entryPoints() {
            return Arrays.asList("android-tutorial-step1", "prod", "us", "es");
        }

        @Override
        String welcomeMessage() {
            return "Have a question about preparing your area?";
        }

        @Override
        List<MessageOption> quickReplies() {
            return Arrays.asList(
                    new MessageOption("Tips when prepping area", "Tips when prepping area"),
                    new MessageOption("I didn't wash or sanitize", "I didn't wash or sanitize"),
                    new MessageOption("Missing / damaged items", "Missing / damaged items"),
                    new MessageOption("Why must I blow my nose?", "Why must I blow my nose?")
            );
        }
    },
    testingStep2 {
        @Override
        List<String> entryPoints() {
            return Arrays.asList("android-tutorial-step2", "prod", "us", "es");
        }

        @Override
        String welcomeMessage() {
            return "Have a question about test setup?";
        }

        @Override
        List<MessageOption> quickReplies() {
            return Arrays.asList(
                    new MessageOption("Test setup tips", "Test setup tips"),
                    new MessageOption("I spilled liquid", "I spilled liquid"),
                    new MessageOption("What's in the liquid?", "What's in the liquid?"),
                    new MessageOption("I touched the test tube", "I touched the test tube")
            );
        }
    },
    testingStep3 {
        @Override
        List<String> entryPoints() {
            return Arrays.asList("android-tutorial-step3", "prod", "us", "es");
        }

        @Override
        String welcomeMessage() {
            return "Need some help with your swab?";
        }

        @Override
        List<MessageOption> quickReplies() {
            return Arrays.asList(
                    new MessageOption("How to swab correctly", "How to swab correctly"),
                    new MessageOption("My nose is congested", "My nose is congested"),
                    new MessageOption("I only swabbed 1 nostril", "I only swabbed 1 nostril"),
                    new MessageOption("I can't finish swabbing", "I can't finish swabbing"),
                    new MessageOption("Something touched my swab", "Something touched my swab")
            );
        }
    },
    testingStep4 {
        @Override
        List<String> entryPoints() {
            return Arrays.asList("android-tutorial-step4", "prod", "us", "es");
        }

        @Override
        String welcomeMessage() {
            return "Have a question about processing your test?";
        }

        @Override
        List<MessageOption> quickReplies() {
            return Arrays.asList(
                    new MessageOption("I dropped in wrong place", "I dropped in wrong place"),
                    new MessageOption("There's not enough liquid", "There's not enough liquid"),
                    new MessageOption("I sneezed on the test", "I sneezed on the test"),
                    new MessageOption("I dropped too many drops", "I dropped too many drops"),
                    new MessageOption("Test was touched/moved", "Test was touched/moved"),
                    new MessageOption("Test processing tips", "Test processing tips"),
                    new MessageOption("I opened test 30+ min ago", "I opened test 30+ min ago")
            );
        }
    },
    testingStep5 {
        @Override
        List<String> entryPoints() {
            return Arrays.asList("android-tutorial-step5", "prod", "us", "es");
        }

        @Override
        String welcomeMessage() {
            return "Have a question about your test result?";
        }

        @Override
        List<MessageOption> quickReplies() {
            return Arrays.asList(
                    new MessageOption("I see 2 lines", "I see 2 lines"),
                    new MessageOption("I see a faint T line", "I see a faint T line"),
                    new MessageOption("Do I need a PCR test?", "Do I need a PCR test?"),
                    new MessageOption("What do my results mean?", "What do my results mean?"),
                    new MessageOption("Help! I tested positive", "Help! I tested positive")
            );
        }
    },
    testingStep6 {
        @Override
        List<String> entryPoints() {
            return Arrays.asList("android-tutorial-step6", "prod", "us", "es");
        }

        @Override
        String welcomeMessage() {
            return "Want to understand more about reporting your test results?";
        }

        @Override
        List<MessageOption> quickReplies() {
            return Arrays.asList(
                    new MessageOption("Do I need to retest?", "Do I need to retest?"),
                    new MessageOption("Who can see my results?", "Who can see my results?"),
                    new MessageOption("How do I report results?", "How do I report results?")
            );
        }
    },
    testingStep7 {
        @Override
        List<String> entryPoints() {
            return Arrays.asList("android-tutorial-step7", "prod", "us", "es");
        }

        @Override
        String welcomeMessage() {
            return "What questions do you have about storing your test kit?";
        }

        @Override
        List<MessageOption> quickReplies() {
            return Arrays.asList(
                    new MessageOption("Temperature & weather", "Temperature & weather"),
                    new MessageOption("I need to reorder", "I need to reorder"),
                    new MessageOption("Does the test expire?", "Does the test expire?")
            );
        }
    };

    abstract List<String> entryPoints();

    abstract String welcomeMessage();

    abstract List<MessageOption> quickReplies();
}

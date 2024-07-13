package app.revanced.integrations.twitter;

import okhttp3.Request;

public class OkHttpClientLimiter {
    private static long lastUpdateTs = 0;
    public static boolean shouldSkipRequest(Request request) {
        final String suffix = "/HomeTimeline";
        final long intervalSecond = 6 * 60 * 60;
        String url = request.url().encodedPath();
        if (!url.endsWith(suffix)) return false;

        long timestamp = System.currentTimeMillis();
        long diff = timestamp - lastUpdateTs;
        System.out.println("diff " + diff + " ts " + timestamp);
        if (diff < intervalSecond * 1000) return true;

        lastUpdateTs = timestamp;
        return false;
    }
}

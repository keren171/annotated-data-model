/******************************************************************************
 ** This data and information is proprietary to, and a valuable trade secret
 ** of, Basis Technology Corp.  It is given in confidence by Basis Technology
 ** and may only be used as permitted under the license agreement under which
 ** it has been distributed, and in no other way.
 **
 ** Copyright (c) 2014 Basis Technology Corporation All rights reserved.
 **
 ** The technical data and information provided herein are provided with
 ** `limited rights', and the computer software provided herein is provided
 ** with `restricted rights' as those terms are defined in DAR and ASPR
 ** 7-104.9(a).
 ******************************************************************************/

package com.basistech.dm;

import com.basistech.util.LanguageCode;

/**
 * A detected language.
 */
public class LanguageDetection extends Attribute {
    private final LanguageCode[] language;
    private final double[] confidence;

    public LanguageDetection(int startOffset, int endOffset, LanguageCode[] language, double[] confidence) {
        super(LanguageDetection.class.getName(), startOffset, endOffset);
        this.language = language;
        this.confidence = confidence;
    }

    public LanguageCode[] getLanguage() {
        return language;
    }

    public double[] getConfidence() {
        return confidence;
    }
}

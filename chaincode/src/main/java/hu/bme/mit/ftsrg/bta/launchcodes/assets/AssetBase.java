/* SPDX-License-Identifier: Apache-2.0 */
package hu.bme.mit.ftsrg.bta.launchcodes.assets;

import com.fasterxml.jackson.core.JsonProcessingException;

import static hu.bme.mit.ftsrg.bta.launchcodes.util.Serializer.*;

public interface AssetBase {
  String getTypeForCompositeKey();

  String[] getAttributesForCompositeKey();

  default String toJsonString() throws JsonProcessingException {
    return serialize(this);
  }
}

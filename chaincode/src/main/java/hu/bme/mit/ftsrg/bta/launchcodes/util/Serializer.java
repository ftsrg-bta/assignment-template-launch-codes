/* SPDX-License-Identifier: Apache-2.0 */
package hu.bme.mit.ftsrg.bta.launchcodes.util;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.experimental.UtilityClass;

@UtilityClass
public class Serializer {

  private final ObjectMapper mapper = new ObjectMapper();

  public String serialize(Object obj) throws JsonProcessingException {
    return mapper.writeValueAsString(obj);
  }

  public <T> T deserialize(String data, Class<T> clazz) throws JsonProcessingException {
    return mapper.readValue(data, clazz);
  }
}

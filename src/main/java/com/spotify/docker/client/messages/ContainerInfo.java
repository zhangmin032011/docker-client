/*
 * Copyright (c) 2014 Spotify AB.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *   http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing,
 * software distributed under the License is distributed on an
 * "AS IS" BASIS, WITHOUT WARRANTIES OR CONDITIONS OF ANY
 * KIND, either express or implied.  See the License for the
 * specific language governing permissions and limitations
 * under the License.
 */

package com.spotify.docker.client.messages;

import static com.fasterxml.jackson.annotation.JsonAutoDetect.Visibility.ANY;
import static com.fasterxml.jackson.annotation.JsonAutoDetect.Visibility.NONE;

import com.fasterxml.jackson.annotation.JsonAutoDetect;
import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.google.auto.value.AutoValue;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.util.Date;
import java.util.List;
import java.util.Map;

@AutoValue
@JsonAutoDetect(fieldVisibility = ANY, setterVisibility = NONE, getterVisibility = NONE)
public abstract class ContainerInfo {

  @NotNull
  @JsonProperty("Id")
  public abstract String id();

  @NotNull
  @JsonProperty("Created")
  public abstract Date created();

  @NotNull
  @JsonProperty("Path")
  public abstract String path();

  @NotNull
  @JsonProperty("Args")
  public abstract List<String> args();

  @NotNull
  @JsonProperty("Config")
  public abstract ContainerConfig config();

  @NotNull
  @JsonProperty("HostConfig")
  public abstract HostConfig hostConfig();

  @NotNull
  @JsonProperty("State")
  public abstract ContainerState state();

  @NotNull
  @JsonProperty("Image")
  public abstract String image();

  @NotNull
  @JsonProperty("NetworkSettings")
  public abstract NetworkSettings networkSettings();

  @NotNull
  @JsonProperty("ResolvConfPath")
  public abstract String resolvConfPath();

  @NotNull
  @JsonProperty("HostnamePath")
  public abstract String hostnamePath();

  @NotNull
  @JsonProperty("HostsPath")
  public abstract String hostsPath();

  @NotNull
  @JsonProperty("Name")
  public abstract String name();

  @NotNull
  @JsonProperty("Driver")
  public abstract String driver();

  @NotNull
  @JsonProperty("ExecDriver")
  public abstract String execDriver();

  @NotNull
  @JsonProperty("ProcessLabel")
  public abstract String processLabel();

  @NotNull
  @JsonProperty("MountLabel")
  public abstract String mountLabel();

  /**
   * Volumes returned by execInspect
   *
   * @return A map of volumes where the key is the source path on the local file system, and the key
   * is the target path on the Docker host.
   * @deprecated Replaced by {@link #mounts()} in API 1.20.
   */
  @Nullable
  @Deprecated
  @JsonProperty("Volumes")
  public abstract Map<String, String> volumes();

  /**
   * Volumes returned by execInspect
   *
   * @return A map of volumes where the key is the source path on the local file system, and the key
   * is the target path on the Docker host.
   * @deprecated Replaced by {@link #mounts()} in API 1.20.
   */
  @Nullable
  @Deprecated
  @JsonProperty("VolumesRW")
  public abstract Map<String, Boolean> volumesRW();

  @NotNull
  @JsonProperty("AppArmorProfile")
  public abstract String appArmorProfile();

  @Nullable
  @JsonProperty("ExecIDs")
  public abstract List<String> execIds();

  @NotNull
  @JsonProperty("LogPath")
  public abstract String logPath();

  @NotNull
  @JsonProperty("RestartCount")
  public abstract Long restartCount();

  @Nullable
  @JsonProperty("Mounts")
  public abstract List<ContainerMount> mounts();

  /**
   * This field is an extension defined by the Docker Swarm API, therefore it will only be populated
   * when communicating with a Swarm cluster.
   */
  @NotNull
  @JsonProperty("Node")
  public abstract Node node();

  @JsonCreator
  static ContainerInfo create(
      @JsonProperty("Id") final String id,
      @JsonProperty("Created") final Date created,
      @JsonProperty("Path") final String path,
      @JsonProperty("Args") final List<String> args,
      @JsonProperty("Config") final ContainerConfig containerConfig,
      @JsonProperty("HostConfig") final HostConfig hostConfig,
      @JsonProperty("State") final ContainerState containerState,
      @JsonProperty("Image") final String image,
      @JsonProperty("NetworkSettings") final NetworkSettings networkSettings,
      @JsonProperty("ResolvConfPath") final String resolvConfPath,
      @JsonProperty("HostnamePath") final String hostnamePath,
      @JsonProperty("HostsPath") final String hostsPath,
      @JsonProperty("Name") final String name,
      @JsonProperty("Driver") final String driver,
      @JsonProperty("ExecDriver") final String execDriver,
      @JsonProperty("ProcessLabel") final String processLabel,
      @JsonProperty("MountLabel") final String mountLabel,
      @JsonProperty("Volumes") final Map<String, String> volumes,
      @JsonProperty("VolumesRW") final Map<String, Boolean> volumesRW,
      @JsonProperty("AppArmorProfile") final String appArmorProfile,
      @JsonProperty("ExecIDs") final List<String> execIds,
      @JsonProperty("LogPath") final String logPath,
      @JsonProperty("RestartCount") final Long restartCount,
      @JsonProperty("Mounts") final List<ContainerMount> mounts,
      @JsonProperty("Node") final Node node) {
    return new AutoValue_ContainerInfo(
        id, created, path, args, containerConfig, hostConfig, containerState, image,
        networkSettings, resolvConfPath, hostnamePath, hostsPath, name, driver, execDriver,
        processLabel, mountLabel, volumes, volumesRW, appArmorProfile, execIds, logPath,
        restartCount, mounts, node);
  }

  @AutoValue
  public abstract static class Node {

    @NotNull
    @JsonProperty("Id")
    public abstract String id();

    @NotNull
    @JsonProperty("Ip")
    public abstract String ip();

    @NotNull
    @JsonProperty("Addr")
    public abstract String addr();

    @NotNull
    @JsonProperty("Name")
    public abstract String name();

    @JsonCreator
    static Node create(
        @JsonProperty("Id") final String id,
        @JsonProperty("Ip") final String ip,
        @JsonProperty("Addr") final String addr,
        @JsonProperty("Name") final String name) {
      return new AutoValue_ContainerInfo_Node(id, ip, addr, name);
    }
  }
}
